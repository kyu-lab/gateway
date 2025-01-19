package com.kyulab.gateway.dto;

import java.util.List;

public record TokenDTO(String id, String userName, List<String> list) {
}
