#!/bin/sh

usbgadget_enabled()
{
    return 0
}

usbgadget_run()
{
    SERIAL_GADGET_DIR="/sys/kernel/config/usb_gadget/g_serial"

    load_kernel_module usb_f_acm
    load_kernel_module libcomposite

    if ! mount -t configfs configfs /sys/kernel/config; then
        msg "Unable to mount configfs."
        return 1
    fi

    if [ ! -d "/sys/kernel/config/usb_gadget" ]; then
        msg "Couldn't find libcomposite path. Make sure that required kernel modules are installed."
        return 1
    fi

    if ! mkdir "$SERIAL_GADGET_DIR"; then
        msg "Unable to create new gadget directory"
        return 1
    fi

    cd "$SERIAL_GADGET_DIR" || return 1

    echo '0x1d6b' > idVendor
    echo '0x0104' > idProduct
    echo '0x0404' > bcdDevice
    echo '0x0200' > bcdUSB
    echo '0x02' > bDeviceClass
    echo '0x00' > bDeviceSubClass
    echo '0x00' > bDeviceProtocol

    mkdir -p strings/0x409

    echo "1234567890" > strings/0x409/serialnumber
    echo "T3" > strings/0x409/manufacturer
    echo "Gemstone" > strings/0x409/product

    mkdir configs/c.1
    echo 500 > configs/c.1/MaxPower

    mkdir -p configs/c.1/strings/0x409
    echo "Gemstone Composite" > configs/c.1/strings/0x409/configuration

    mkdir functions/acm.usb0
    ln -s functions/acm.usb0 configs/c.1/

    ls /sys/class/udc > UDC
}
