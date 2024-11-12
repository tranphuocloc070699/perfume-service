package com.loctran.service.entity.productNote.dto;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.loctran.service.entity.productNote.ProductNote;

import java.io.IOException;

public class ProductNoteSerializer extends JsonSerializer<ProductNote> {
    @Override
    public void serialize(ProductNote productNote, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", productNote.getId());
        gen.writeStringField("name", productNote.getName());
        gen.writeStringField("enName", productNote.getEnName());
        gen.writeStringField("slug", productNote.getSlug());
        gen.writeStringField("thumbnail", productNote.getThumbnail());
        gen.writeEndObject();
    }
}