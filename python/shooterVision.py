from networktables import NetworkTables
import cv2
import numpy as np

NetworkTables.initialize(server='10.6.13.59') #change to roborio IP if it does not work

table = NetworkTables.getTable('datatable') #whatever the name of the table we use is

camera_feed = cv2.VideoCapture(0) #cam number will change depending on order of cams plugged in at boot
#camera_feed.set(cv2.CAP_PROP_CONTRAST, -100)

xCentroids = [0,0,0,0,0]
counter = 0
detected = False

while(1):
    #camera_feed.set(15,-10)
    #print camera_feed.get(15)
    _,frame = camera_feed.read() #single frame from cam
    frame = cv2.GaussianBlur(frame,(5,5),0)

    hsv = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV) #convert frame to HSV 
    hsv = cv2.GaussianBlur(hsv,(5,5),0)

    lowerT = np.array([132,59,123]) #lower threshold (Hue,Sat,Val)
    upperT = np.array([179,224,255]) #upper threshold

    #hsv = cv2.fastNlMeansDenoisingColored(hsv,None,10,10,7,21)

    mask = cv2.inRange(hsv, lowerT, upperT) #filters the frame based on hsv threshold
    
    mask = cv2.GaussianBlur(mask,(5,5),0)

    #Smoothes image (probably not needed for this)
    #element = cv2.getStructuringElement(cv2.MORPH_RECT,(3,3))
    #mask = cv2.erode(mask,element, iterations=2) 
    #mask = cv2.dilate(mask,element,iterations=2)
    #mask = cv2.erode(mask,element)

    _,contours, hierarchy = cv2.findContours(mask, cv2.RETR_EXTERNAL, cv2.CHAIN_APPROX_SIMPLE) #All contours
    maximumArea = 0 
    bestContour = None
    secondBestContour = None
    #i = 0
    for contour in contours: #iterates through all contours
        (x,y),radius = cv2.minEnclosingCircle(contour) #tries a circle around the blob
        circArea = 3.14*radius*radius #circle area
        currentArea = cv2.contourArea(contour) #area of the blob
        x,y,w,h = cv2.boundingRect(contour) #tries a rectangle around the blob
        rectArea = 1.0*h*w #rectangle area
        if rectArea - currentArea <= circArea - currentArea: #rectangle should be closer to blob area than circle area
            if currentArea > maximumArea and currentArea > 300: #if it's the new biggest blob and the size is over 300
                #if w > h: #oriented horizontally (not needed anymore)
                    secondBestContour = bestContour #old biggest contour
                    bestContour = contour #current biggest contour
                    maximumArea = currentArea #area of current biggest contour

    if bestContour is not None: #if best contour has a value
        detected = True
        x,y,w,h = cv2.boundingRect(bestContour) #rectangle
        cv2.rectangle(frame, (x,y),(x+w,y+h), (0,0,255), 3) #draws the rectangle over the colour image
        cnt = bestContour #for moments
        moment = cv2.moments(cnt) #creates a moment
        #Rolling average with past two values to reduce impact of outliers and smooth data output
        xCentroids[counter] = int(moment['m10']/moment['m00'])
        counter = counter + 1
        if counter == 3:
            counter = 0
        xCentroids[4] = (xCentroids[0]*1.0+xCentroids[1]+xCentroids[2]+xCentroids[3])/4
        _,frameW = frame.shape[:2]
        xCentroids[4] = xCentroids[4] - (frameW/2) #distance from the center
        table.putNumber("xValue",xCentroids[4])
        table.putNumber("blobWidth",w)
        table.putNumber("blobHeight",h)
        table.putNumber("detected",1)
        print xCentroids[4]
    else:
        if detected:
            table.putNumber("detected", 0)
            detected = False
            print 'not detected'

    cv2.imshow('frame',frame) #displays image (comment out on pi)
    
    cv2.imshow('mask',mask) #comment out on pi but it's good for debugging
    
    k = cv2.waitKey(5) & 0xFF #on computer esc kills it
    if k == 27:
        break


cv2.destroyAllWindows() #not needed on pi
