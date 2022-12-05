package coding.challenge.common.api.model;


import lombok.Builder;

@Builder
public record ErrorModel(int code, String message) { }
