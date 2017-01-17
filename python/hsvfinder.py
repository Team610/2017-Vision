import cv2
import numpy as np
from pymouse import PyMouse
import time
feed = cv2.VideoCapture(1)


 
# load the image, clone it, and setup the mouse callback function


mouse = PyMouse()

_, frame = feed.read()
cv2.namedWindow("feed")
width = np.size(frame, 1) #/ 2
height = np.size(frame,0) #/ 2

mouseW = mouse.position()[0]
mouseH = mouse.position()[1]

mousePos = (mouseW, mouseH)
#    frameSize = (width, height)

w = (width/1280.0) * mouseW
h = (height/800.0) * mouseH

pos = (w,h)

hsvImage = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
minH = hsvImage[h,w][0]
minS = hsvImage[h,w][1]
minV = hsvImage[h,w][2]
maxH = hsvImage[h,w][0]
maxS = hsvImage[h,w][1]
maxV = hsvImage[h,w][2]

cv2.circle(frame,(int(pos[0]),int(pos[1])), 3, (0,0,255), -1)

while(1):
    _, frame = feed.read()
    cv2.namedWindow("feed")
    width = np.size(frame, 1) #/ 2
    height = np.size(frame,0) #/ 2

    mouseW = mouse.position()[0]
    mouseH = mouse.position()[1]

    mousePos = (mouseW, mouseH)
#    frameSize = (width, height)

    w = (width/1280.0) * mouseW
    h = (height/800.0) * mouseH

    pos = (w,h)
    
    hsvImage = cv2.cvtColor(frame, cv2.COLOR_BGR2HSV)
    minH = min(hsvImage[h,w][0], minH)
    minS = min(hsvImage[h,w][1], minS)
    minV = min(hsvImage[h,w][2], minV)
    maxH = max(hsvImage[h,w][0], maxH)
    maxS = max(hsvImage[h,w][1], maxS)
    maxV = max(hsvImage[h,w][2], maxV)
    print (minH, minS, minV)
    print (maxH, maxS, maxV)
    
    cv2.circle(frame,(int(pos[0]),int(pos[1])), 3, (0,0,255), -1)
    
    cv2.imshow('frame',frame)
    

cv2.destroyAllWindows() #not needed on pi
    
