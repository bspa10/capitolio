#version 130

in vec3 v_color;
in vec2 v_texture;

out vec4 o_color;

void main() {
	o_color = vec4(v_color, 1.0);
//	o_color = texture(tex, v_texture);
}
