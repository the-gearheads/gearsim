#version 450

layout (location=0) in vec3 position;
layout (location=1) in vec4 inColor;

out vec4 outColor;

uniform mat4 projMat;

void main()
{
    gl_Position = projMat * vec4(position, 1.0);
    outColor = inColor;
}
