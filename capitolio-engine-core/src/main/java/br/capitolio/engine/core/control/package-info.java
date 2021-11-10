/**
 * Abstração para obtenção das entradas do jogador.
 *
 * O Engine irá utilizar o callback de entra do GLFW para
 * disponibilizar um objeto estático que conterá o estado atual
 * dos dispositivos de entrada.
 *
 * Dessa forma o jogo final utilizará o gestor de entrada do engine
 * e não o GLFW diretamente.
 *
 */
package br.capitolio.engine.core.control;
