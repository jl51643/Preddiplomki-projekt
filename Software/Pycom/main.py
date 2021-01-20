from network import LoRa
import socket
import ubinascii
import struct
import pycom
import time
import binascii
import crypto
from machine import RTC
import utime

from pysense import Pysense
import machine
from SI7006A20 import SI7006A20


def Random():
   r = crypto.getrandbits(32)
   return ((r[0]<<24)+(r[1]<<16)+(r[2]<<8)+r[3])/90000000.0

# Initialise LoRa in LORAWAN mode.
lora = LoRa(mode=LoRa.LORAWAN, region=LoRa.EU868)

# create an ABP authentication params
dev_addr = struct.unpack(">l", ubinascii.unhexlify('260110D3'))[0]
nwk_swkey = ubinascii.unhexlify('91523EB6FEF3530CD38527D867EFC85E')
app_swkey = ubinascii.unhexlify('20F9279DED83CE5B12C9022BBD9E5011')

# join a network using ABP (Activation By Personalization)
lora.join(activation=LoRa.ABP, auth=(dev_addr, nwk_swkey, app_swkey))

# create a LoRa socket
s = socket.socket(socket.AF_LORA, socket.SOCK_RAW)

# set the LoRaWAN data rate
s.setsockopt(socket.SOL_LORA, socket.SO_DR, 0)

print('Joined ABP')

py = Pysense()
si = SI7006A20(py)
while True:

    temperature = si.temperature()
    print('Temp: ' + str(temperature) + ' Celsius')
    humidity = si.humidity()
    print('Humidity: ' + str(humidity) + '% \n')
    payload = struct.pack(">ff", temperature,humidity)
    try:
        s.send(payload)
    except:
        pass

    time.sleep(10)