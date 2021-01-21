
// Put your libraries here (#include ...)
#include <WaspBG96.h>
#include <WaspFrame.h>
#include <WaspSensorEvent_v30.h>

char apn[] = "NBIOT";
char login[] = "";
char password[] = "";

char network_operator[] = "A1 HR";

uint8_t operator_type = LALPHANUMERIC_OPERATOR;

char band[] = B20;

char host[] = "http://178d0430b55a.ngrok.io";
uint16_t port = 80;
char resource[200];

uint8_t error;

void setup()
{
  USB.ON();
  USB.println(F("Start program\n"));

  //postavljanje parametara operatora
  BG96.set_APN(apn, login, password);
  BG96.show_APN();

}


void loop()
{
  Events.ON();
  // set identifier
  /*frame.setID("Waspmote");
  // Create new frame (ASCII)
  frame.createFrame(ASCII);
  // set frame fields ()
  frame.addSensor(SENSOR_EVENTS_LUXES, Events.getLuxes(INDOOR));
  // show frame contents
  frame.showFrame();

  // define aux buffer
  char frame_string[frame.length*2 + 1];
  memset(frame_string, 0x00, sizeof(frame_string));

  // convert frame from bytes to ASCII representation
  Utils.hex2str((uint8_t*)frame.buffer, (char*)frame_string, frame.length);*/


  //snprintf(resource, sizeof(resource), "/?frame=%s", frame_string);
  snprintf(resource, sizeof(resource), "/api/measurement/waspmote/add?dev_id=%s&luminosity=%lu", "Waspmote_NB", Events.getLuxes(INDOOR));
  

  USB.print(F("1. Data to send: "));
  for(int i = 0; i<sizeof(resource); i++){
    USB.print(resource[i]);
  }
  USB.println();

  
  //upali NB-Iot modul
  error = BG96.ON();

  if(error == 0){
    USB.println(F("1. NB-IoT module ready"));

    //konfigururaj konekciju
    error = BG96.nbiotConnection(apn, band, network_operator, operator_type);

    if(error == 0){
      USB.println(F("1.1. NB-IoT connection: OK "));

      //uspostavi konekciju
      error = BG96.contextActivation(1,5);

      if(error == 0){
        //ispisi IP adresu na usb
        USB.println(F("1.2. NB-IoT context connection: OK "));
        USB.print(F("IP: ")); USB.println(BG96._ip);

        //postavi DNS poslužitelj
        error = BG96.setDNSServer("8.8.8.8","8.8.4.4");
        if (error == 0)
        {
          USB.println(F("2. Setting DNS server: OK "));
        }
        else
        {
          USB.println(F("2. DNS error: ")); USB.println(error,DEC);
          USB.println(BG96._buffer, BG96._length);
        }

        //pošalji HTTP GET na poslužitelj sa podatcima u queriju
        error = BG96.http(WaspBG96::HTTP_GET, host, port, resource);
        
        //provjeri odgovor
        if (error == 0)
        {
          USB.print(F("3. Done. HTTP code: "));
          USB.println(BG96._httpCode);
          USB.print("Server response: ");
          USB.println(BG96._buffer, BG96._length);
        }
        else
        {
          USB.print(F("3. Failed. Error code: "));
          USB.println(error, DEC);
        }
        
      }
    }
  }
  else{
    //ako je došlo do greške kod paljenja NB-Iot modula
    USB.println(F("NB-IoT module not started"));
    USB.print(F("Error code: "));
    USB.println(error, DEC);
  }

  //ugasi NB-Iot modul
  error = BG96.OFF();
   if (error == 0)
    {
      USB.println(F("4. Module is power off"));
    }
    else
    {
      USB.println(F("4. Power off ERROR"));
    }

  //stavi Waspmote u deep sleep mode na neko vrijeme
  USB.println(F("5. Enter deep sleep..."));
  PWR.deepSleep("00:00:00:05", RTC_OFFSET, RTC_ALM1_MODE1, ALL_OFF);  //spavaj 5s

  USB.ON();
  USB.println(F("6. Wake up!!\n\n"));

}
