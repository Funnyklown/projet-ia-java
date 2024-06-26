public class FFTSon {

    private final Complexe[] fftSon;

    //Constructeur qui retourne la FFT d'un son et la stocke dans fftSon
    public FFTSon(String path, int tailleBloc){
        Son son = new Son(path);
        Complexe[] EchantillonsCplx = new Complexe[tailleBloc];

        //On récupère un bloc de taille TailleBloc pour pas faire exploser la FFT qui nécessite une puissance de 2
        float[] bloc = son.bloc_deTaille(1, tailleBloc);

        for(int i = 0; i < tailleBloc; i++){
            EchantillonsCplx[i] = new ComplexeCartesien(bloc[i], 0);
        }

        this.fftSon = FFTCplx.appliqueSur(EchantillonsCplx);
    }

    public Complexe[] getFftSon(){
        return this.fftSon;
    }
    //Retourne un tableau de flottants contenant les modules des complexes de la FFT
    public float[] getFftSonMod(){
        float[] tab_mod = new float[this.fftSon.length];
        for( int i = 0 ; i < this.fftSon.length ; i++){
            tab_mod[i]=(float)this.fftSon[i].mod();
        }
        return tab_mod;
    }
    //Retourne un tableau de flottants contenant les arguments des complexes de la FFT
    public float[] getFftSonArg(){
        float[] tab_arg = new float[this.fftSon.length];
        for( int i = 0 ; i < this.fftSon.length ; i++){
            tab_arg[i]=(float)this.fftSon[i].arg();
        }
        return tab_arg;
    }
}