public class Detecteur {

    private iNeurone n;
    private final float[][] entrees;
    private final float[] sorties;
    private final int tailleBloc;
    private int nombreTours;

    public Detecteur(String[] entrees_paths, float[] sorties, int tailleBloc){
        this.tailleBloc = tailleBloc;
        this.entrees = new float[entrees_paths.length][tailleBloc];

        this.sorties = new float[sorties.length];
        System.arraycopy(sorties, 0, this.sorties, 0, sorties.length); // On met sorties dans this.sorties

        for(int i = 0; i < entrees_paths.length; i++){
            FFTSon son = new FFTSon(entrees_paths[i], tailleBloc);
            this.entrees[i] = son.getFftSonMod();
            //this.entrees[i] = son.getFftSonArg();
        }
    }

    public void learn(float[][] entrees, float[] sorties){
        this.n = new NeuroneSigmoide(this.tailleBloc);
        this.nombreTours = this.n.apprentissage(entrees, sorties);
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

    public float[] getSorties(){
        return this.sorties;
    }

    public float[][] getEntrees(){
        return this.entrees;
    }

    public int getNombreTours(){
        return this.nombreTours;
    }

    public static void main(String[] args) {
        String pathToTest = "./sons/Bruit.wav";
        String[] entrees_paths = {"./sons/Sinusoide.wav", "./sons/Sinusoide2.wav", "./sons/Bruit.wav"};
        float[] sorties = {1, 1, 0};


        Detecteur detecteur = new Detecteur(entrees_paths, sorties, 512);

        detecteur.learn(detecteur.getEntrees(), detecteur.getSorties());
        float result = detecteur.test(pathToTest);
        System.out.println("Nombre de tours: " + detecteur.getNombreTours());
        System.out.println("Resultat : " + result);
    }
}
