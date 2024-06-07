public class Detecteur {

    private final Son son;
    private Complexe[] EchantillonsCplx;
    private Complexe[] resultat;

    private static final int TailleBloc = 512; //Puissance de 2 sinon tout explose
    private static final String path = "./sons/Sinusoide.wav"; //Fichier son à traiter

    public Detecteur(){
        this.son = new Son(path);
        this.EchantillonsCplx = new Complexe[TailleBloc];

        //On récupère un bloc de taille TailleBloc pour pas faire exploser la FFT qui nécessite une puissance de 2
        float[] bloc = this.son.bloc_deTaille(1, TailleBloc);

        for(int i = 0; i < TailleBloc; i++){
            this.EchantillonsCplx[i] = new ComplexeCartesien(bloc[i], 0);
        }

        this.resultat = FFTCplx.appliqueSur(this.EchantillonsCplx);
    }

    public static void main(String[] args){
        Detecteur detecteur = new Detecteur();
        for (int i = 0; i < detecteur.resultat.length; ++i) {
            System.out.print(i+" : ("+(float)detecteur.resultat[i].reel()+" ; "+(float)detecteur.resultat[i].imag()+"i)");
            System.out.println(", ("+(float)detecteur.resultat[i].mod()+" ; "+(float)detecteur.resultat[i].arg()+" rad)");
        }
    }
}
