package br.fiap.utils;

import br.fiap.fornecedor.Fornecedor;
import br.fiap.produto.Produto;

import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;
import static java.lang.Long.parseLong;

public class Util {
    private Fornecedor[] fornecedor = new Fornecedor[3];
    private Produto produto[] = new Produto[3];
    private int idxProduto;
    private int idxFornecedor;

    public void menu() {
        String menu = "MENU PRINCIPAL\n";
        menu += "1. Cadastrar Produto\n";
        menu += "2. Pesquisar Produto por NOME\n";
        menu += "3. Pesquisar Produto por CNPJ\n";
        menu += "4. Finalizar\n";

        while (true) {
            int opcao = parseInt(showInputDialog(null, menu));

            switch (opcao) {
                case 1:
                    cadastrarProduto();
                    break;

                case 2:
                    pesquisarProduto();
                    break;

                case 3:
                    pesquisar();
                    break;

                case 4:
                    finalizar();
                    return;

                default:
                    erro();
                    break;
            }
        }

    }

    private void cadastrarProduto() {
        String nome;
        int qtdEstoque;
        double valorUni;
        Fornecedor fornecedor = pesquisarFornecedor();
        if (fornecedor == null) {
            fornecedor = cadastrarFornecedor();
        }
        nome = showInputDialog("Nome do produto:");
        qtdEstoque = parseInt(showInputDialog("Quantidade em estoque do produto " + nome));
        valorUni = parseDouble(showInputDialog("Preço unitário do produto " + nome));
        produto[idxProduto] = new Produto(nome, valorUni, qtdEstoque, fornecedor);
        idxProduto++;
    }

    private void pesquisarProduto() {
        String aux = "Produto não encontrado";
        String nome = showInputDialog("Nome do produto:");
        for (int i = 0; i < idxProduto; i++) {
            if (produto[i].getNome().equalsIgnoreCase(nome)) {
                aux = "";
                aux += "Nome do produto: " + nome + "\n";
                aux += "Valor unitário do produto: " + produto[i].getValor() + "\n";
                aux += "Fornecedor do produto: " + produto[i].getFornecedor().getNome() + "\n";
                aux += "Quantidade do produto em estoque: " + produto[i].getQtdEstoque() + "\n";
            }
        }
        showMessageDialog(null, aux);
    }

    private void pesquisar() {
        String aux = "";
        Fornecedor fornecedor = pesquisarFornecedor();

        if (fornecedor != null) {
            aux += "Fornecedor: " + fornecedor.getNome() + "\n";
            aux += "CNPJ: " + fornecedor.getCnpj() + "\n";
            showMessageDialog(null, aux);
        }
    }

    private Fornecedor pesquisarFornecedor() {
        long cnpj = parseLong(showInputDialog("CNPJ do fornecedor"));
        for (int i = 0; i < idxFornecedor; i++) {
            if (fornecedor[i].getCnpj() == cnpj) {
                return fornecedor[i];
            }
        }
        showMessageDialog(null, "CNPJ não cadastrado");
        return null;
    }

    private Fornecedor cadastrarFornecedor() {
        String nome = showInputDialog("Nome do fornecedor:");

        long cnpj = parseLong(showInputDialog("CNPJ do fornecedor:"));
        fornecedor[idxFornecedor] = new Fornecedor(nome, cnpj);
        idxFornecedor++;
        return fornecedor[idxFornecedor - 1];
    }

    // GERAL
    private void finalizar() {
        showMessageDialog(null, "Finalizando programa...");
    }

    private void erro() {
        showMessageDialog(null, "Opção inexistente, tente novamente");
    }
}
