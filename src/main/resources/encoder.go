package kafkaprotocol

import (
    "bytes"
    "encoding/binary"
)

type Encodable interface {
    encode(enc Encoder) error
}

type Encoder struct {
    buffer *bytes.Buffer
}

func NewEncoder() *Encoder {
    buf := new(bytes.Buffer)
    return &Encoder{
        buffer: buf,
    }
}

func (enc *Encoder) WriteInt8(i int8) {
    binary.Write(enc.buffer, binary.BigEndian, i)
}

func (enc *Encoder) WriteInt16(i int16) {
    binary.Write(enc.buffer, binary.BigEndian, i)
}

func (enc *Encoder) WriteInt32(i int32) {
    binary.Write(enc.buffer, binary.BigEndian, i)
}

func (enc *Encoder) WriteInt64(i int64) {
    binary.Write(enc.buffer, binary.BigEndian, i)
}

func (enc *Encoder) WriteString(s string) {
    if s == "" {
        enc.WriteInt16(-1)
    } else {
        enc.WriteInt16(int16(len(s)))
        binary.Write(enc.buffer, binary.BigEndian, s)
    }
}

func (enc *Encoder) WriteInt32Array(a []int32) {
    arrayLength := len(a)
    enc.WriteInt32(int32(arrayLength))
    for i := 0; i < arrayLength; i++ {
        enc.WriteInt32(a[i])
    }
}

func (enc *Encoder) WriteStringArray(strings []string) {
    arrayLength := len(strings)
    enc.WriteInt32(int32(arrayLength))
    for i := 0; i < arrayLength; i++ {
        enc.WriteString(strings[i])
    }
}

func (enc *Encoder) Len() int {
    return enc.buffer.Len()
}
