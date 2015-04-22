#!/usr/bin/python
import paho.mqtt.client as mqtt
import time
import spidev
import pyrobot

unit = "unit5"

# SPI sensor functions
def ReadChannel(channel):
        adc = spi.xfer2([1,(8+channel)<<4,0])
        data = ((adc[1]&3) << 8) + adc[2]
        return data

def ConvertVolts(data, places):
        volts = (data * 3.3) / float(1023)
        volts = round(volts, places)
        return volts

# MQTT function
def on_connect(client, userdata, flags, rc):
	print("Connected with result code "+str(rc))
	client.subscribe("/pbl1/"+unit+"/create/#")

def on_message(client, userdata, msg):
	print(msg.topic+" "+str(msg.payload))
	action = msg.topic[msg.topic.rindex('/')+1:]
	print(action)
	if action == "forward":
		create.Drive(100,0)
	elif action == "back":
		create.Drive(-100,0)
	elif action == "left":
		create.Drive(100,100)
	elif action == "right":
		create.Drive(100,-100)
	elif action == "stop":
		create.Stop()

# setup create
create = pyrobot.Create()
create.Control()

# setup spi sensor
spi = spidev.SpiDev()
spi.open(0,0)

# setup mqtt
client = mqtt.Client()
# callbacks
client.on_connect = on_connect 
client.on_message = on_message

client.connect("mqtt.f.ait.kyushu-u.ac.jp", 1883, 60)
client.loop_start()

while True:
	light_level = ReadChannel(0)
	client.publish("/pbl1/unit5/light",light_level)
	time.sleep(1)
