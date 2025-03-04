% Initialisation de Matlab
clear;
close all;
clc;

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Effet de l'échantillonnage : Génération et tracé d'un cosinus numérique
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%1. Générer 90 échantillons d'un cosinus, d'amplitude 1 (V), de fréquence 
%   f0 = 1100 Hz et échantillonné à Fe = 10000 Hz

%Paramètres
f0 = 1100;        %fréquence du cosinus
Fe = 10000;       %fréquence d'échantillonnage
Te = 1 / Fe;      %période d'échantillonnage
N = 90;           %nombre d'échantillons

%Génération du signal
x = cos(2 * pi *f0 * (0 : Te : (N - 1) * Te));

%2. tracé du cosinus généré précédemment avec une échelle temporelle en 
%   secondes

%Tracé du signal
figure;
plot((0 : Te : (N - 1) * Te), x)
xlabel('Temps en secondes')
ylabel('Signal de X')
title('figure 1 : Signal de X avec Fe = 10000 Hz')

%3. Générer 90 échantillons d'un cosinus, d'amplitude 1 (V), de fréquence
%   f0 = 1100 Hz et échantillonné à Fe = 1000 Hz
 
%Paramètres
f0 = 1100;        %fréquence du cosinus
Fe = 1000;        %fréquence d'échantillonnage
Te = 1 / Fe;      %période d'échantillonnage
N = 90;           %nombre d'échantillons

%Génération du signal
x = cos(2 * pi *f0 * (0 : Te : (N - 1) * Te));

%4. tracé du cosinus généré précédemment avec une échelle temporelle en 
%   secondes

%Tracé du signal
figure;
plot((0:Te:(N-1) * Te), x)
xlabel('Temps en secondes')
ylabel('Signal de X')
title('figure 2 : Signal de X avec Fe = 1000 Hz')

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Transformée de Fourier discrète (TFD)
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%2.a) estimation de la transformée de Fourier d'un cosinus de fréquence 
%     f0 = 1100 Hz, d'amplitude A = 1V et échantillonné à Fe = 10000 Hz

%Calcul de la TFD du signal

%Paramètres
f0 = 1100;        %fréquence du cosinus
Fe = 10000;       %fréquence d'échantillonnage
Te = 1 / Fe;      %période d'échantillonnage
N = 90;           %nombre d'échantillons

%Génération du signal
x = cos(2 * pi *f0 * (0:Te:(N-1) * Te));
X = fft(x);

%Tracé du module de la TFD du signal
figure;
semilogy((0:Te:(N-1) * Te), abs(X))
xlabel('Fréquence en HZ')
ylabel('TFD du signal')
title('figure 3 : La transformée de Fourier avec Fe = 10000')


%2.b) estimation de la transformée de Fourier d'un cosinus de fréquence 
%     f0 = 1100 Hz, d'amplitude A = 1V et échantillonné à Fe = 1000 Hz

%Paramètres
f0 = 1100;        %fréquence du cosinus
Fe = 1000;        %fréquence d'échantillonnage
Te = 1 / Fe;      %période d'échantillonnage
N = 90;           %nombre d'échantillons

%Génération du signal
x = cos(2 * pi *f0 * (0:Te:(N-1) * Te));
X = fft(x);

%Tracé du module de la TFD du signal
figure;
semilogy((0:Te:(N-1) * Te), abs(X))
xlabel('Fréquence en HZ')
ylabel('TFD du signal')
title('figure 4 : La transformée de Fourier avec Fe = 1000')

%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% TFD du signal x 
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%4) calcul de la transformée de Fourier d'un cosinus de fréquence 
%   f0 = 1100, échantillonné à Fe = 1000 en utilisant  et la technique du 
%   Zero Padding

% Paramètres
f0 = 1100;           % fréquence du cosinus
Fe = 10000;          % fréquence d'échantillonnage
Te = 1 / Fe;         % période d'échantiollonnage
N = 90;              % pas d'échantiollannage

% Génération du signal
x = cos(2 * pi * f0 * (0 : Te : (N - 1) * Te));
Nprime = 2 ^ nextpow2(length(x));          % Nombre de points
X = fft(x, Nprime);

% Tracé du module de cette transformé de Fourier en échelle log 
figure;
semilogy(linspace(-1 / 2 * Te, 1 / 2 * Te, length(X)), abs(X));
xlabel('Fréquence en Hz');
ylabel('|TF(x)|');
title('figure 5 : |TF(x)| en utilisant Zero Padding');

%5) Zero Padding avec plusieurs points

% Paramètres
Te = 1 / 10000;              % période d'échantiollonnage
N = 90;                      % pas d'échantiollannage
f0 = 1100;                   % fréquence du cosinus

% Génération du signal
x = cos( 2 * pi * f0 * (0 : Te : (N - 1) * Te));
N1 = 2 ^ nextpow2(length(x));      
X = fft(x, N1);

% Tracé de la 1ière figure
figure;
semilogy(linspace(-1 / 2 * Te, 1 / 2 * Te, length(X)), abs(X));
xlabel('Fréquence en Hz');
ylabel('|TF(x)|');
title('figure 6 : |TF(x)| en utilisant Zero Padding pour plusieurs points');
hold on;

% Tracé de la 2ière figure
N2 = 256;
X = fft(x, N2);
semilogy(linspace(-1 / 2 * Te, 1 / 2 * Te, length(X)), abs(X));

% Tracé de la 3ière figure
N3 = 1024;
X = fft(x, N3);
semilogy(linspace(-1 / 2 * Te, 1 / 2 * Te, length(X)), abs(X));


%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Densité spectrale de puissance (DSP) numérique
%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

%1) Générer une réalisation du signal x

% Paramètres
Te = 1 / 10000;             % période d'échantiollonnage
N = 90;                     % pas d'échantiollannage
T = 0 : Te : (N - 1) * Te;
f0 = 1100;                  % fréquence de cosinus
phi=rand*2*pi;              % phase à l'origine
legend('N=128','N=256','N=1024'); 

% Génération du signal
x = cos( 2 * pi * f0* T + phi);

%2) La DSP du signal x
X = fft(x);
F = linspace(-1 / 2 * Te, 1 / 2 * Te, length(X)); 
DSP_x = (1 / N) * (abs(X)) .^ 2;

% Tracé de la DSP du signal
figure;
plot((0 : Te : (N - 1) * Te), DSP_x);
xlabel('temps en seconds');
ylabel('DSP du signal x');
title('figure 7 : DSP du signal');

%3) la DSP du signal x, en implantant un périodogramme avec un fenêtrage

% Paramètres
Te = 1 / 10000;      % période d'échantiollonnage
N = 90;              % pas d'échantiollannage
f0 = 1100;           % fréquence de cosinus
phi = rand * 2 * pi; % phase à l'origine

% Génération du signal
x = cos(2 * pi * f0 * T + phi);

% une fenêtre de Hamming
X1 = x .* hamming(N)';
X = fft(X1, N);
DSP_x = (1 / N) * (abs(X)) .^ 2;

% Tracé la DSP du signal x, en implantant un fenetrage
figure;
plot((0 : Te : (N - 1) * Te), DSP_x);
xlabel('temps en seconds');
ylabel('DSP du signal x');
title('figure 8 : DSP du signal en implantant Hamming et Blackman');
hold on;

% Paramètres
Te= 1 / 10000;               % période d'échantiollonnage
N = 90;                      % pas d'échantiollannage
f0 = 1100;                   %fréquence de cosinus
phi = rand * 2 * pi;         % phase à l'origine

% Génération du signal
x = cos(2 * pi*  f0* T + phi);

% une fenêtre de Blackman
X1 = x .* blackman(N)';
X = fft(X1, N);
DSP_x = (1 / N) * (abs(X)) .^ 2;

% Tracé la DSP du signal x, en implantant un fenetrage
plot((0 : Te : (N - 1) * Te), DSP_x);
hold off;
legend('hamming','blackman'); 