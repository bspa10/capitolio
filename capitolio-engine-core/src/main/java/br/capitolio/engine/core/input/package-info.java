/**
 * <p><strong>Abstração da captura de comandos de entrada.</strong></p>
 * <br>
 * <p>
 *     O sistema de entrada utiliza duas abstrações.
 *     <br>
 *     <br>
 *     A primeira é a <strong>key mapping</strong> onde uma combinação arbitrária de teclas,
 *     feita com o uso de um {@link br.capitolio.engine.core.input.action.InputCombination InputCombination}
 *     é mapeada para uma ação nomeada. Isto é, o código cliente faz o mapeamento, por exemplo,
 *     das teclas SHIFT + W para que o jogador ande para frente lentamente. No ato de mapear é dado
 *     um nome ao comando, digamos <i>andar-devagar</i>.
 *     <br>
 *     <br>
 *     A segunda é a <strong>key bind</strong> onde uma ação é vinculada a um <strong>key mapping</strong>.
 *     A ação é uma implementação de {@link br.capitolio.engine.core.input.action.AbstractAction AbstractAction}
 * </p>
 * <br>
 * <p>
 *     O código cliente fará uso direto deste módulo. As ações
 * </p>
 */
package br.capitolio.engine.core.input;
