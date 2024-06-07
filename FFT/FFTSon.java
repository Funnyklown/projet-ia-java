public class FFTSon {


    private Complexe[] fftSon;

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
}
