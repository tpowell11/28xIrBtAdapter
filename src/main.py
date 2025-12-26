# bluetooth script cobbled together from
# https://github.com/micropython/micropython-lib/blob/master/micropython/bluetooth/aioble/examples/temp_sensor.py
# as well as the programming manual for the 28x meters:
# https://www.eevblog.com/forum/reviews/going-further-with-the-fluke-289/?action=dlattach;attach=16945
# and the official bluetooth specs for the GATT and the Assigned Numbers document.
import sys
import asyncio
import aioble
import bluetooth
from machine import UART, Pin

_ADV_INTERVAL_US = 250_000

_GATT_SERVICE_UUID = bluetooth.UUID(0x1801)

_GATT_CHAR_DEFAULTSETUP = bluetooth.UUID(0b0000_0000_0000_0001) # For use with the DS command
_GATT_CHAR_IDENTIFICATION = bluetooth.UUID(0b0000_0000_0000_0010) # For use with the ID command
_GATT_CHAR_RESETINSTRUMENT = bluetooth.UUID(0b0000_0000_0000_0100)# For use with the RI command
_GATT_CHAR_RESETMETERPROP = bluetooth.UUID(0b0000_0000_0000_1000) # For use withh the RMP command
_GATT_CHAR_QUERYPRIMARYMEASUREMENT = bluetooth.UUID(0b0000_0000_0001_0000) # For use with the QM command
_GATT_CHAR_QUERYDISPLAYEDDATA = bluetooth.UUID(0b0000_0000_0010_0000) # For use with the QDDA command

#### Charachteristic & Service  Declarations
gatt_service = aioble.Service(_GATT_SERVICE_UUID)
ds_char = aioble.Characteristic(gatt_service,_GATT_CHAR_DEFAULTSETUP,read=True)
id_char = aioble.Characteristic(gatt_service, _GATT_CHAR_IDENTIFICATION,read=True)
ri_char = aioble.Characteristic(gatt_service, _GATT_CHAR_RESETINSTRUMENT,read=True)
rmp_char = aioble.Characteristic(gatt_service, _GATT_CHAR_RESETMETERPROP,read=True)
qm_char = aioble.Characteristic(gatt_service, _GATT_CHAR_QUERYPRIMARYMEASUREMENT, read=True)
qdda_char = aioble.Characteristic(gatt_service, _GATT_CHAR_QUERYDISPLAYEDDATA, read=True)
aioble.register_services(gatt_service)

#### 28x Functions
def send_message(message: str) -> str :
    "Send `message` over optical RS232 and wait for a response"
    uart0 = UART(0,
        baudrate=115200,
        bits=8,
        parity=None,
        stop=1,
        tx=Pin(0),
        rx=Pin(1),
        timeout_char=10 #inter-char timeout in ms
    )
    res: bytes = uart0.read()
    return res.decode("ascii", errors='ignore')
    
def command(command: str, char: aioble.Characteristic) -> None :
    res = send_message(command)
    char.write(to_bin(res))

def to_bin(string: str) -> bytes :
    return string.encode("utf-8")

async def handle_ds():
    command("DS\r") 
    
async def handle_id():
    command("ID\r")
    
async def handle_ri():
    command("RI\r")

async def handle_rmp():
    command("RMP\r")

async def handle_qm():
    command("QM\r")

async def handle_qdda():
    command("QDDA\r")

async def server_loop():
    while True:
        async with await aioble.advertise (
            _ADV_INTERVAL_US,
            name="28xIrBt",
            services=[_GATT_SERVICE_UUID],
            appearance=0x1480,
        ) as connection:
            print("Connection from:", connection.device)
            await connection.disconnected(timeout_ms=None)

async def main():
    ds_task = asyncio.create_task(handle_ds())
    id_task = asyncio.create_task(handle_id())
    ri_task = asyncio.create_task(handle_ri())
    rmp_task = asyncio.create_task(handle_rmp())
    qm_task = asyncio.create_task(handle_qm())
    qdda_task = asyncio.create_task(handle_qdda())
    server_task = asyncio.create_task(server_loop())
    await asyncio.gather(
        ds_task,
        id_task,
        ri_task,
        rmp_task,
        qm_task,
        qdda_task,
        server_task
    )
