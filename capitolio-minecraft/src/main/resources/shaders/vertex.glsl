#version 130

attribute vec3 position;
attribute vec3 color;
attribute vec2 textureCoord;

out vec3 v_color;
out vec2 v_textureCoord;

void main() {
	gl_Position = vec4(position, 1.0);
	v_color = color;
	v_textureCoord = textureCoord;
}
