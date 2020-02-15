package com.program.shader;

import org.joml.Matrix4f;
import org.lwjgl.opengl.GL20;
import org.lwjgl.system.MemoryStack;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Shader {
    private int program;
    private int vertexShader;
    private int fragmentShader;
    private final Map<String, Integer> uniforms = new HashMap<>();

    public Shader() throws Exception{
        program = GL20.glCreateProgram();
        if (program == 0) {
            throw new Exception("Shader program failed to create.");
        }
    }

    private int createShader(String code, int type) throws Exception {
        int shader = GL20.glCreateShader(type);
        if (shader == 0) {
            throw new Exception("Failed to create shader.");
        }

        GL20.glShaderSource(shader, code);
        GL20.glCompileShader(shader);

        if(GL20.glGetShaderi(shader, GL20.GL_COMPILE_STATUS) == 0) {
            throw new Exception("Failed to compile shader: " + GL20.glGetShaderInfoLog(shader));
        }
        GL20.glAttachShader(program, shader);

        return shader;
    }

    public void linkProgram() throws Exception {
        GL20.glLinkProgram(program);

        if (vertexShader != 0) {
            GL20.glDetachShader(program, vertexShader);
        }
        if (fragmentShader != 0) {
            GL20.glDetachShader(program, fragmentShader);
        }

        GL20.glValidateProgram(program);
    }

    public void bindProgram() {
        GL20.glUseProgram(program);
    }

    public void unbindProgram() {
        GL20.glUseProgram(program);
    }

    public void removeProgram() {
        unbindProgram();
        if (program != 0) {
            GL20.glDeleteProgram(program);
        }
    }

    public void createVertexShader(String code) throws Exception {
        vertexShader = createShader(code, GL20.GL_VERTEX_SHADER);
    }

    public void createFragmentShader(String code) throws Exception {
        fragmentShader = createShader(code, GL20.GL_FRAGMENT_SHADER);
    }

    public void createUniform(String name) throws Exception {
        int uniform = GL20.glGetUniformLocation(program, name);
        System.out.println(uniform);
        if (uniform < 0) {
            throw new Exception("Could not find specified uniform '" + name + "'");
        }
        uniforms.put(name, uniform);
    }

    public void setUniform(String name, Matrix4f mat) {
        try (MemoryStack stack = MemoryStack.stackPush()) {
            FloatBuffer buffer = stack.mallocFloat(16);
            mat.get(buffer);
            GL20.glUniformMatrix4fv(uniforms.get(name), false, buffer);
        }
    }

    public static String loadShaderFile(String path) {
        String code = "";
        try {
            code = new String(
                    Files.readAllBytes(
                            Paths.get(path)
                    ));
            //code = code + "\0";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }
}
