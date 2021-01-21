to #include <WaspLoRaWAN.h>
#include <WaspSensorEvent_v30.h>
uint8_t socket = SOCKET0;

char DEVICE_EUI[]  = "00F29048B7914723";
char DEVICE_ADDR[] = "260117A2";
char NWK_SESSION_KEY[] = "97C11C3F0B95BDA7AB3068A48A52FA43";
char APP_SESSION_KEY[] = "1058EEBFFD791F1C68F10071B312CCB4";

uint8_t PORT = 1;
uint8_t error;

float floatTemp;
float floatVlaga;
float floatTlak;

uint8_t payload[12];
uint16_t payload_size = sizeof(payload);

void setup() 
{
  USB.ON();

  LoRaWAN.ON(socket);
  LoRaWAN.setDeviceEUI(DEVICE_EUI);
  LoRaWAN.setDeviceAddr(DEVICE_ADDR);
  LoRaWAN.setNwkSessionKey(NWK_SESSION_KEY);
  LoRaWAN.setAppSessionKey(APP_SESSION_KEY);
  LoRaWAN.saveConfig();
  
  Events.ON();
}


void loop() 
{
  floatTemp = Events.getTemperature();
  floatVlaga = Events.getHumidity();
  floatTlak = Events.getPressure();

  uint32_t temp = floatTemp * 100;
  uint32_t vlaga = floatVlaga * 100;
  uint32_t tlak = floatTlak * 100;

  payload[0] = temp >> 24;
  payload[1] = temp >> 16;
  payload[2] = temp >> 8;
  payload[3] = temp;

  payload[4] = vlaga >> 24;
  payload[5] = vlaga >> 16;
  payload[6] = vlaga >> 8;
  payload[7] = vlaga;

  payload[8] = tlak >> 24;
  payload[9] = tlak >> 16;
  payload[10] = tlak >> 8;
  payload[11] = tlak;

  LoRaWAN.ON(socket);  
  LoRaWAN.joinABP();
 
  LoRaWAN.sendUnconfirmed( PORT, payload, payload_size);

  LoRaWAN.OFF(socket);
  delay(10000);
}
