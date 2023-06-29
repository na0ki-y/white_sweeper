# -*- coding: utf-8 -*-
#pip3 install fastapi uvicorn
import uvicorn
from fastapi import FastAPI
from motor_control import motor_controler,Mode
app = FastAPI()
mc=None

@app.get("/")
def root(cmd_str: str = "None"):#/?cmd_str=ForwardLeft
    print("root",cmd_str)
    mc.motors_control(Mode.FRONT)
    return {"message": "cmd_str:"+cmd_str}

@app.get("/STOP")
def stop():
    print("STOP")
    mc.motors_control(Mode.STOP)
    return {"message": "STOP"}
@app.get("/ForwardLeft")
def right():
    print("ForwardLeft")
    mc.motors_control(Mode.LEFT)
    return {"message": "ForwardLeft"}
@app.get("/TurnForwardLeft")
def right():
    print("TurnForwardLeft")
    mc.motors_control(Mode.TURN_LEFT)
    return {"message": "TurnForwardLeft"}
@app.get("/Forward")
def right():
    print("Forward")
    mc.motors_control(Mode.FRONT)
    return {"message": "Forward"}


@app.get("/TurnForwardRight")
def front():
    print("TurnForwardRight")
    mc.motors_control(Mode.TURN_RIGHT)
    return {"message": "TurnForwardRight"}

@app.get("/ForwardRight")
def right():
    print("ForwardRight")
    mc.motors_control(Mode.RIGHT)
    return {"message": "ForwardRight"}

####
@app.get("/BackLeft")
def right():
    print("BackLeft")
    mc.motors_control(Mode.LEFT_BACK)
    return {"message": "BackLeft"}
@app.get("/TurnBackLeft")
def right():
    print("TurnBackLeft")
    mc.motors_control(Mode.TURN_LEFT)
    return {"message": "TurnBackLeft"}
@app.get("/Back")
def right():
    print("Back")
    mc.motors_control(Mode.BACK)
    return {"message": "Back"}
@app.get("/TurnBackRight")
def front():
    print("TurnBackRight")
    mc.motors_control(Mode.TURN_RIGHT)
    return {"message": "TurnBackRight"}
@app.get("/BackRight")
def right():
    print("BackRight")
    mc.motors_control(Mode.RIGHT)
    return {"message": "BackRight"}



if __name__ == "__main__":
    mc = motor_controler((14,15),(17,18)) # ピンの指定
    mc.motors_control(Mode.STOP)
    try:
        uvicorn.run(app, host="0.0.0.0", port=8000)
    finally:
        print("err")
        mc.motors_control(Mode.STOP)
        del mc #cleanup
    