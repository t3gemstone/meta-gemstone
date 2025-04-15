#!/bin/sh

gemfinish_enabled()
{
    return 0
}

clean_gadget()
{
    echo '' > "$SERIAL_GADGET_DIR/UDC" 2>/dev/null || true

    for dir in "$SERIAL_GADGET_DIR"/configs/*/strings/*; do
        [ -d "$dir" ] && rmdir "$dir"
    done

    for func in "$SERIAL_GADGET_DIR"/configs/*.*/*.*; do
        [ -e "$func" ] && rm "$func"
    done

    rm -f "$SERIAL_GADGET_DIR"/os_desc/*.*

    for conf in "$SERIAL_GADGET_DIR"/configs/*; do
        [ -d "$conf" ] && rmdir "$conf"
    done

    for func in "$SERIAL_GADGET_DIR"/functions/*.*; do
        for dir in "$func"/os_desc/*/*; do
            [ -d "$dir" ] && rmdir "$dir"
        done

        [ -d "$func" ] && rmdir "$func"
    done

    for str in "$SERIAL_GADGET_DIR"/strings/*; do
        [ -d "$str" ] && rmdir "$str"
    done

    rmdir "$SERIAL_GADGET_DIR"

    return 0
}

gemfinish_run()
{
    [ -d "$SERIAL_GADGET_DIR" ] && clean_gadget

    return 0
}
