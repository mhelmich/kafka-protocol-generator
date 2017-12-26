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

func (enc *Encoder) WriteInt16(i int16) {
    binary.Write(enc.buffer, binary.BigEndian, i)
}

func (enc *Encoder) WriteInt32(i int32) {
    binary.Write(enc.buffer, binary.BigEndian, i)
}

func (enc *Encoder) Len() int {
    return enc.buffer.Len()
}
