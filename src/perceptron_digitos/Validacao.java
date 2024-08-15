package perceptron_digitos;

public class Validacao {

    public Validacao() {
    }

    public double somatorio(int[][] mat, double[][] weight, int k) {
        double yent = 0;
        double[] entrada = new double[16];
        int l = 1;
        entrada[0] = 1;
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 3; j++) {
                entrada[l] = mat[i][j];
                l++;
            }
        for (int j = 0; j < 16; j++)
            yent = yent + entrada[j] * weight[j][k];
        return yent;
    }

    public double saida(double yent, double limiar) {
        return (yent > limiar) ? 1 : (yent < -limiar) ? -1 : 0;
    }

    public String teste(int[][] mat, double[][] weight, double[][] target, double limiar) {
        double[] results = new double[4];
        for (int j = 0; j < 4; j++) {
            double yent = somatorio(mat, weight, j);
            results[j] = saida(yent, limiar);
        }

        for (int i = 0; i < 10; i++) {
            boolean match = true;
            for (int j = 0; j < 4; j++) {
                if (results[j] != target[i][j]) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return String.valueOf(i);
            }
        }
        return "?";
    }
}