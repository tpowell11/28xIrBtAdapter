# Firmware
This firmware is intended for a Raspberry Pi Pico 2 W.
It is implemented in micropython using the [`aioble`](https://github.com/micropython/micropython-lib/blob/master/micropython/bluetooth/aioble/README.md) package.
Each command for the meter is its own Bluetooth [GATT](https://www.bluetooth.com/wp-content/uploads/Files/Specification/HTML/Core-54/out/en/host/generic-attribute-profile--gatt-.html) endpoint.
The Bluetooth parameters used in the firmware are shown below:
| Parameter                                     | Value    |
|-----------------------------------------------|----------|
| Avertisement Interval                         | 250000us |
| GATT Service UUID                             | 0x1801   |
| Default Setup Characteristic UUID             | 0x1      |
| Identification Characteristic UUID            | 0x2      |
| Reset Instrument Characteristic UUID          | 0x4      |
| Reset Meter Properties Characteristic UUID    | 0x8      |
| Query Primary Measurement Characteristic UUID | 0x10     |
| Query Displayed Data Characteristic UUID      | 0x20     |

