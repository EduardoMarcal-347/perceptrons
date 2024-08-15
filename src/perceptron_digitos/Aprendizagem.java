package perceptron_digitos;

public class Aprendizagem {

    private final double[][] numbersImg ={
            {1,1,1,1,1,0,1,1,0,1,1,0,1,1,1,1},
            {1,0,1,0,1,1,0,0,1,0,0,1,0,1,1,1},
            {1,1,1,1,0,0,1,1,1,1,1,0,0,1,1,1},
            {1,1,1,1,0,0,1,1,1,1,0,0,1,1,1,1},
            {1,1,0,1,1,0,1,1,1,1,0,0,1,0,0,1},
            {1,1,1,1,1,0,0,1,1,1,0,0,1,1,1,1},
            {1,1,1,1,1,0,0,1,1,1,1,0,1,1,1,1},
            {1,1,1,1,0,0,1,0,0,1,0,0,1,0,0,1},
            {1,1,1,1,1,0,1,1,1,1,1,0,1,1,1,1},
            {1,1,1,1,1,0,1,1,1,1,0,0,1,1,1,1}
    };

    private final double[][] weight = new double [16][4];
    private final double[][] target = {
            {1, 1, 1, 1},
            {1, 1, 1, -1},
            {1, 1, -1, 1},
            {1, 1, -1, -1},
            {1, -1, 1, 1},
            {1, -1, 1, -1},
            {1, -1, -1, 1},
            {1, -1, -1, -1},
            {-1, 1, 1, 1},
            {-1, 1, 1, -1}
    };
    private int epocas;

    public Aprendizagem(){
        epocas=0;
    }

    public double [][] getWeight(){
        return weight;
    }

    public double [][] getTarget(){
        return target;
    }

    public int getEpocas(){
        return epocas;
    }

    public double somatorio(int i, int k){
        double yent=0;
        for(int j=0;j<16;j++)
            yent = yent + numbersImg[i][j] * weight[j][k];
        return yent;
    }

    public double saida(double yent, double limiar){
        return (yent > limiar) ? 1 : (yent < -limiar) ? -1 : 0;
    }

    public void atualiza( double alfa, double[] f, int ePos){
        for(int i=0;i<4;i++)
            for(int j=0; j<16; j++)
                weight[j][i] = weight[j][i] + alfa * (target[ePos][i]-f[i]) * numbersImg[ePos][j];
    }

    public void algoritmo(double alfa, double limiar){
        double yent;
        double[] result = {0,0,0,0};
        boolean mudou;

        for (int i=0;i<16;i++)
            for (int j=0; j<4; j++)
                weight[i][j]=0; // zerar os pesos

        do{
            mudou = false;
            for (int i = 0; i < 10; i++) {   // 10 padrões de entrada (digito 0) ~ (digito 9)
                for (int j = 0; j < 4; j++) {
                    yent = somatorio(i,j);
                    result[j] = saida(yent, limiar);
                    if (result[j] != target[i][j]) {
                        mudou = true;
                    }
                }
                if (mudou) {
                    atualiza(alfa, result, i);
                }
            }
            epocas++;
        }while(mudou);

        for(double[] arr : weight) {
            System.out.print("[ ");
            for(double element: arr) {
                System.out.print(element +", ");
            }
            System.out.println("]");
        }

    }
}
