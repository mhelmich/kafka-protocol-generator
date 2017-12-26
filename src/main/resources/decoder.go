package kafkaprotocol

import (
    "bufio"
    "encoding/binary"
    "fmt"
)

type Decodable interface {
    decode(dec Decoder) error
}

type Decoder struct {
    decoder *bufio.Reader
}

func (dec *Decoder) ReadInt64() (ret int64) {
    binary.Read(dec.decoder, binary.BigEndian, &ret)
    return
}

func (dec *Decoder) ReadInt32() (ret int32) {
    binary.Read(dec.decoder, binary.BigEndian, &ret)
    return
}

func (dec *Decoder) ReadInt16() (ret int16) {
    binary.Read(dec.decoder, binary.BigEndian, &ret)
    return
}

func (dec *Decoder) ReadInt8() (ret int8) {
    binary.Read(dec.decoder, binary.BigEndian, &ret)
    return
}

func (dec *Decoder) ReadString() (ret string) {
    strLength := dec.ReadInt16()
    if int(strLength) == -1 {
        ret = ""
        return
    } else {
        buf := make([]byte, strLength)
        n, err := dec.decoder.Read(buf)
        if err != nil || n != int(strLength) {
            fmt.Printf("Couldn't read %d bytes but only %d\n", strLength, n)
        }
        ret = string(buf)
        return
    }
}

func (dec *Decoder) ReadBytes() (ret []byte) {
    bitesLength := dec.ReadInt32()
    if int(bitesLength) == -1 {
        ret = make([]byte, 0)
        return
    } else {
        buf := make([]byte, bitesLength)
        n, err := dec.decoder.Read(buf)
        if err != nil || n != int(bitesLength) {
            fmt.Printf("Couldn't read %d bytes but only %d\n", bitesLength, n)
        }
        ret = buf
        return
    }
}

func (dec *Decoder) ReadInt32Array() (ret []int32) {
    arrayLength := dec.ReadInt32()
    if int(arrayLength) == -1 {
        ret = nil
        return
    } else {
        buf := make([]int32, arrayLength)
        var i int32
        for i = 0; i < arrayLength; i++ {
            buf[i] = dec.ReadInt32()
        }
        ret = buf
        return
    }
}

func (dec *Decoder) ReadStringArray() (ret []string) {
    arrayLength := dec.ReadInt32()
        if int(arrayLength) == -1 {
        ret = nil
        return
    } else {
        buf := make([]string, arrayLength)
        var i int32
        for i = 0; i < arrayLength; i++ {
            buf[i] = dec.ReadString()
        }
        ret = buf
        return
    }
}

func (dec *Decoder) Remaining() int {
    return dec.decoder.Buffered()
}
