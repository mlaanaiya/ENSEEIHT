%--------------------------------------------------------------------------
% ENSEEIHT - 1SN - Télécommunications
% TP2 - Etudes de chaines de transmission sur fréquence porteuse
% chaine_pb.m
%--------------------------------------------------------------------------

%--------------------------------------------------------------------------
% Initialisation
%--------------------------------------------------------------------------
clear;
close all;
clc;

%%
%--------------------------------------------------------------------------
% Utilisation de la chaine passe-bas équivalente pour le calcul et
% l’estimation du taux d’erreur binaire
%--------------------------------------------------------------------------

% Initialisation des paramètres
Fe = 10000;
Te = 1 / Fe;
alpha = 0.35;
span = 8;
fp = 2000;
fc = 1500;
Rs = 1000;
nb_bits = 10000;
Ns = Fe / Rs;
N = 50;

%%
%--------------------------------------------------------------------------
% Implantation de la chaine sans bruit
%--------------------------------------------------------------------------
% Génération de l’information binaire
bits = randi([0, 1], 1, nb_bits);

% Mapping permettant d'obtenir dk ∈ {±1 ± j}
ak = 2 * bits(1 : 2 : end) - 1;
bk = 2 * bits(2 : 2 : end) - 1;
dk = ak + 1j * bk;

% Génération de la suite de Diracs pondérés par les symbols (suréchantillonnage)
Suite_diracs1 = kron(ak, [1 zeros(1, Ns - 1)]);
Suite_diracs2 = kron(bk, [1 zeros(1, Ns - 1)]);

% Génération de la réponse impulsionnelle du filtre de mise en forme
h = rcosdesign(alpha, 8, Ns, 'sqrt');
retard = (span * Ns) / 2;

% Filtrage de mise en forme du signal généré sur la voie I
I = filter(h, 1, [Suite_diracs1 zeros(1, retard)]);
I = I(retard + 1 : end);

% Filtrage de mise en forme du signal généré sur la voie Q
Q = filter(h, 1, [Suite_diracs2 zeros(1, retard)]);
Q = Q(retard + 1 : end);

% L’enveloppe complexe associée au signal transmis modulé
T = [0 : length(I) - 1] * Te;
x_e =  I + 1i * Q;

% Affichage du signal génerée sur la voie en phase
figure ;
plot(I);
axis([0 length(I) - 1 -1 1]);
title('Signal généré sur la voie en phase I');
xlabel('Temps en secondes');
ylabel('I(t)');

% Affichage du signal généré sur la voie en quadrature
figure ;
plot(Q);
axis([0 length(Q) - 1 -1 1]);
title('Signal généré sur la voie en quadrature Q');
xlabel('Temps en secondes');
ylabel('Q(t)');


%%
% Calcul de la DSP  de l’enveloppe complexe associée au signal transmis 
% modulé sur fréquence porteuse par périodogramme
DSP_x_e = (1 / length(x_e)) * abs(fft(x_e, 2 ^ nextpow2(length(x_e)))) .^ 2;

% Affichage de la DSP de l’enveloppe complexe associée au signal transmis 
% modulé sur fréquence porteuse par périodogramme
figure;
plot(linspace(-Fe / 2, Fe / 2, length(DSP_x_e)), fftshift(DSP_x_e));
title('DSP de l’enveloppe complexe associée au signal transmis modulé');
xlabel('Fréquences en Hz');
ylabel('S_{x_{e}}(f)');

%%
% Génération de la réponse impulsionnelle du filtre de réception
h_r = h;

% Filtrage de réception
z = filter(h_r, 1, [x_e zeros(1,retard)]);
z = z(retard + 1 : end);

% Affichage du signal reçu
figure ;
plot(real(z));
axis([0 nb_bits - 1 -1.5 1.5]);
title('Figure 1 : signal reçu z sans bruit');
xlabel('Temps en secondes');
ylabel('Signal reçu z');

% Le diagramme de l’oeil en sortie du filtre de réception
diagramme_oeil = reshape(real(z), Ns, length(real(z)) / Ns);
figure;
plot(diagramme_oeil);
title("Diagramme de l'oeil sans bruit du signal reçu");
xlabel('Temps en secondes');

% Echantillonnage du signal
z_echant = z(1 : Ns : end);

% Detecteur à seuil
for i = 1 : length(z_echant)
    if (real(z_echant(i)) <= 0 && imag(z_echant(i)) <= 0)
        symboles_decides(i) = -1 - 1i;
        
    elseif (real(z_echant(i)) >= 0 && imag(z_echant(i)) >= 0)
        symboles_decides(i) = 1 + 1i;
        
    elseif (real(z_echant(i)) <= 0 && imag(z_echant(i)) >= 0)
        symboles_decides(i) = -1 + 1i;
        
    elseif (real(z_echant(i)) >= 0 && imag(z_echant(i)) <= 0)
        symboles_decides(i) = 1 - 1i;
    end
end

% Calcul du TES
TES = length(find(symboles_decides ~= dk)) / (length(dk));

% Calcul du TEB
TEB = TES / log2(4)

%%
%--------------------------------------------------------------------------
% Implantation de la chaine avec bruit
%--------------------------------------------------------------------------
TES = zeros(1,7);
TEB = zeros(1,7);

for i = 0 : 6
    % L'ajout du bruit blanc gaussien
    Puiss_sign = mean(abs(x_e) .^ 2);
    Puiss_bruit = Puiss_sign * Ns  / (2 * log2(4) * 10 .^ (i / 10));
    Bruit_gauss = (sqrt(Puiss_bruit) * randn(1, length(x_e))) + 1i * (sqrt(Puiss_bruit) * randn(1, length(x_e)));
    y = x_e + Bruit_gauss; 
    
    % Génération de la réponse impulsionnelle du filtre de réception
    h_r = h;
    
    % Filtrage de réception
    z = filter(h_r, 1, [y zeros(1,retard)]);
    z = z(retard + 1 : end);
    
    % Echantillonnage du signal
    z_echant = z(1 : Ns : end);
    
    % Les constellations en sortie du mapping et de l’échantillonneur
    figure;
    plot(real(z_echant), imag(z_echant), 'r*');
    hold on;
    plot(ak, bk, 'b*');
    legend('Les constellations en sortie du mapping','Les constellations en sortie de l’échantillonneur')
    xlabel('I');
    ylabel('Q');
    
    % Detecteur à seuil
    for j = 1 : length(z_echant)
        if (real(z_echant(j)) <= 0 && imag(z_echant(j)) <= 0)
            symboles_decides(j) = -1 - 1i;
        elseif (real(z_echant(j)) >= 0 && imag(z_echant(j)) >= 0)
            symboles_decides(j) = 1 + 1i;
        elseif (real(z_echant(j)) <= 0 && imag(z_echant(j)) >= 0)
            symboles_decides(j) = -1 + 1i;
        elseif (real(z_echant(j)) >= 0 && imag(z_echant(j)) <= 0)
            symboles_decides(j) = 1 - 1i;
        end
    end
    
    % Calcul du TES
    TES(i + 1) = length(find(symboles_decides ~= dk)) / (length(dk));
    
    % Calcul du TEB
    TEB(i + 1) = TES(i + 1) / 2;
end

%%
% Comparaison entre le taux d’erreur binaire (TEB) obtenu en fonction Eb/N0
% et le TEB théorique
figure;
semilogy([0 : 6], TEB);
grid
title('TEB');
xlabel("$\frac{Eb}{N_{o}}$ (dB)", 'Interpreter', 'latex');
ylabel('TEB');

%%
% Comparaison entre le taux d’erreur binaire (TEB) obtenu en fonction Eb/N0
% et le TEB théorique
figure;
semilogy([0 : 6], TEB, 'r*');
hold on
semilogy([0 : 6], (4 * (1 - (1 / sqrt(4))) * qfunc(sqrt(3 * log2(4)* 10 .^ ([0 : 6] / 10) / (3)))) / log2(4));
grid
title('Comparaison entre le TEB théorique et estimé');
legend('TEB estimé','TEB théorique')
xlabel("$\frac{Eb}{N_{o}}$ (dB)", 'Interpreter', 'latex');
ylabel('TEB');
