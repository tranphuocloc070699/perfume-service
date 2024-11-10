package com.loctran.service.entity.productNote;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;

public class ProductNoteSerializer extends StdSerializer<ProductNote> {

  public ProductNoteSerializer() {
    this(null);
  }

  public ProductNoteSerializer(Class<ProductNote> t) {
    super(t);
  }

  @Override
  public void serialize(ProductNote productNote, JsonGenerator gen, SerializerProvider provider) throws IOException {
    gen.writeStartObject();
    gen.writeNumberField("id", productNote.getId());
    // Add other fields if needed
    gen.writeEndObject();
  }
}
