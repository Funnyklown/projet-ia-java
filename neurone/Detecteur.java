public class Detecteur {

    private iNeurone n;
    private float[][] entrees;
    private float[] sorties;
    private int tailleBloc;

    public Detecteur(int tailleBloc){
        
        this.tailleBloc = tailleBloc;
        FFTSon sinus = new FFTSon("../sons/Sinusoide.wav", tailleBloc);
        Complexe[] tab = sinus.getFftSon();
        float[] tab_mod = new float[tailleBloc];

        for( int i = 0 ; i < tailleBloc ; i++){

            tab_mod[i]=(float)tab[i].mod();
        }
        this.entrees[0] =tab_mod;
        this.sorties[0] = 1;

    }

    public void learning(float[][] entrees, float[] sorties){
        this.n = new NeuroneSigmoide(entrees[0].length);
        this.n.apprentissage(entrees, sorties);
    }

    public float test(String path){

        FFTSon check = new FFTSon(path,tailleBloc);
        Complexe[] tab = check.getFftSon();
        float[] tab_mod = new float[tailleBloc];

        for( int i = 0 ; i < tailleBloc ; i++){

            tab_mod[i]=(float)tab[i].mod();
        }
        this.n.metAJour(tab_mod);
        return this.n.sortie();

    }

    public static void main(String[] args) {
        Detecteur detecteur = new Detecteur(512);
        detecteur.learning(null, null);
    }
}
