# Hardware: Electrical
## Fluke's IR-UART
From [this](https://www.eevblog.com/forum/reviews/going-further-with-the-fluke-289/?action=dlattach;attach=16945) document,
we get the commands and the serial port parameters,
but not the specifications of the IR link system.
I had suspected that this was a pair of IR LEDs hooked up to a UART,
but did not have any confirmation.
I asked on the [EEVBlog Forum](https://www.eevblog.com/forum/testgear/fluke-287289-ir-protocol/) and user indeterminate confirmed that the IR link was a UART with IR leds.
The link uses:
| Parameter | Value  |
|-----------|--------|
| Baudrate  | 115200 |
| Data Bits | 8      |
| Stop Bits | 1      |
| Parity    | None   |

## The Implementation
The core of this implementation is the Pi Pico 2 W.
This microcontroller module uses a RP2350 and a Infineon CYW43439 WiFi/Bluetooth chip.
