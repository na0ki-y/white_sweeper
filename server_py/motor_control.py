import RPi.GPIO as GPIO
import sys
import time
from enum import Enum

class Mode(Enum):
    STOP=0
    FRONT=1
    BACK=2
    TURN_RIGHT=3
    TURN_LEFT=4
    RIGHT=5
    LEFT=6
    RIGHT_BACK=7
    LEFT_BACK=8

class motor_controler:
    def __init__(self, pin1, pin2):
        self.motor1_pin = pin1
        self.motor2_pin = pin2

        GPIO.setmode(GPIO.BCM)
        GPIO.setup(self.motor1_pin[0], GPIO.OUT)
        GPIO.setup(self.motor1_pin[1], GPIO.OUT)
        GPIO.setup(self.motor2_pin[0], GPIO.OUT)
        GPIO.setup(self.motor2_pin[1], GPIO.OUT)
    
    def __del__(self):
        # これをしないと、usedのままになってしまい次回使えない
        GPIO.cleanup()
    
    def motors_control(self, mode):
        if mode == Mode.STOP: # 停止
            self.motor_control(self.motor1_pin, 0)
            self.motor_control(self.motor2_pin, 0)
        elif mode == Mode.FRONT: # 前進
            self.motor_control(self.motor1_pin, 1)
            self.motor_control(self.motor2_pin, 1)
        elif mode == Mode.BACK: # 後退
            self.motor_control(self.motor1_pin, 2)
            self.motor_control(self.motor2_pin, 2)
        elif mode == Mode.TURN_RIGHT: # 右旋回
            self.motor_control(self.motor1_pin, 1)
            self.motor_control(self.motor2_pin, 2)
        elif mode == Mode.TURN_LEFT: # 左旋回
            self.motor_control(self.motor1_pin, 2)
            self.motor_control(self.motor2_pin, 1)
        elif mode == Mode.RIGHT: # 右
            self.motor_control(self.motor1_pin, 1)
            self.motor_control(self.motor2_pin, 3)
        elif mode == Mode.LEFT: # 左
            self.motor_control(self.motor1_pin, 3)
            self.motor_control(self.motor2_pin, 1)
        elif mode == Mode.RIGHT_BACK: # 右後ろ
            self.motor_control(self.motor1_pin, 2)
            self.motor_control(self.motor2_pin, 3)
        elif mode == Mode.LEFT_BACK: # 左後ろ
            self.motor_control(self.motor1_pin, 2)
            self.motor_control(self.motor2_pin, 1)
        else:
            pass
    
    def motor_control(self, motor_pin, mode):
        if mode == 0: # ストップ
            GPIO.output(motor_pin[0], GPIO.LOW)
            GPIO.output(motor_pin[1], GPIO.LOW)
        elif mode == 1: # 正転
            GPIO.output(motor_pin[0], GPIO.LOW)
            GPIO.output(motor_pin[1], GPIO.HIGH)
        elif mode == 2: # 逆転
            GPIO.output(motor_pin[0], GPIO.HIGH)
            GPIO.output(motor_pin[1], GPIO.LOW)
        elif mode == 3: # ブレーキ
            GPIO.output(motor_pin[0], GPIO.HIGH)
            GPIO.output(motor_pin[1], GPIO.HIGH)
        else:
            pass



# 時間制御は自分で書く
def main():
    mc = motor_controler((14,15),(17,18))
    mc.motors_control(Mode.FRONT)
    time.sleep(20)
    # mc.motors_control(Mode.RIGHT)
    # time.sleep(2)
    # mc.motors_control(Mode.LEFT)
    # time.sleep(2)
    # mc.motors_control(Mode.STOP)


if __name__ == "__main__":
    main()
