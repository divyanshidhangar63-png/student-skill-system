package com.kiet.studentskillsystem;

import java.util.List;

public record UpdateRequest(double programmingScore, double mathematicsScore, List<String> skills) { }