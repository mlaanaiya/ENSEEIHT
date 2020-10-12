Caml1999M023����            %tp.ml����  �  �  
�  	ꠠ���1ocaml.ppx.context��&_none_A@ �A����������)tool_name���*ppx_driver@@����,include_dirs����"[]@@����)load_path!����
%@%@����,open_modules*����.@.@����+for_package3����$None8@8@����%debug=����%falseB@B@����+use_threadsG����
K@K@����-use_vmthreadsP����T@T@����'cookiesY����"::^����������,inline_tests@i@����(disabled@��.<command-line>A@@�A@J@@@r@�����w����������,library-name@�@����#tp1@��A@E@@@�@�����s�@�@@�@�@@�@�@@@�@@�@����@�����/coeff_directeur��%tp.mlM���M��@�@@��@@������"x1��M���M��@�@@����"y1��M���M��@�@@@��M���M��@@��@@������"x2��'M���(M��@�@@����"y2��0M���1M��@�@@@��4M���5M��@@������(failwith��>M���?M��@�@@��@���%TO DO@��HM���IM��@@@�@@�A@�2A@���)ocaml.doc鐠�����
  T 
   coeff_directeur : float*float -> float*float -> float
   calcule le coefficient directeur de la droite représentée par deux points
   Parametre (x1, y1) : float*float, le premier point
   Parametre (x2, y2) : float*float, le second point
   Resultat : float, le coefficient directeur de la droite passant par
   (x1, y1) et (x2, y2)
@��ZE{{�[L��@@@@@��]M��@@�@���@�����$ieme��id���jd��@�@@��@@���!t��sd���td��@�@@��@@���!i��}d���~d��@�@@��@��������%item1���e����e��@�@@����%item2���e����e��@�@@����%item3���e����e��@�@@@���e����e��@@����!t���e����e��@�@@@���e��@@��������!=���f����f��@@��@����!i���f����f��@�@@��@���!1@���f����f��@@@�@@����%item1���g����g��@�@@���������+���j����j��@@��@����!i���j����j��@�@@��@���!2@���j����j��@@@�@@����%item2�� k���k��@�@@�����%item3��
m	�m@�@@��j��@@��f��@@�d@@��A@��	A@@��d��@@�@�����*ocaml.text��������
   padovan : int -> int
Fonction qui calcule la nième valeur de la suite de Padovan : u(n+3) = u(n+1) + u(n); u(2)=1, u(1)=u(0)=0 
Paramètre n : un entier représentant la nième valeur à calculer
Précondition : n >=0
Résultat : un entier la nième valeur de la suite de Padovan 
@��&z))�'GI@@@@���@�����'padovan��2 AKO�3 AKV@�@@��@@���!n��< AKW�= AKX@�@@������(failwith��G AK[�H AKc@�@@��@���%TO DO@��Q AKd�R AKk@@@�@@�A@@��V AKK@@�@���@�����*estPremier��b W	�	��c W	�	�@�@@��@@���!n��l W	�	��m W	�	�@�@@������(failwith��w W	�	��x W	�	�@�@@��@���%TO DO@��� W	�	��� W	�	�@@@�@@�A@@��� W	�	�@@�@�����q%�������	# Création de l'écran d'affichage @��� jaa�� ja�@@@@���@�����&dragon��� w���� w��@�@@��@@������"xa��� w���� w��@�@@����"ya��� w���� w��@�@@@��� w���� w��@@��@@������"xb��� w���� w��@�@@����"yb��� w���� w��@�@@@��� w���� w��@@��@@���!n��� w���� w��@�@@������(failwith��� w���� w��@�@@��@���%TO DO@��� w���� w��@@@�@@�A@�#A@�=A@������������
  � 
   dragon : (int*int) -> (int*int) -> int -> unit
   Dessine la courbe du dragon à partir de deux points et d'une précision.
   Parametre (xa,ya) : (int*int), coordonnées de la première extrémité du dragon
   Paramètre (xb,yb) : (int*int), coordonnées de la seconde extrémité du dragon
   Paramètre n : nombre d'itération (plus n est grand, plus la courbe aura de détails)
   Resultat : unit, affichage de la courbe du dragon sur l'écran
   Précondition : n positif ou nul
@�� n��� v��@@@@@�� w��@@�@��������������	# Fermeture de l'écran d'affichage @�� {� {A@@@@@