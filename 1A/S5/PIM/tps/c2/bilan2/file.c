/**
 *  \author Xavier Cr�gut <nom@n7.fr>
 *  \file file.c
 *
 *  Objectif :
 *	Implantation des op�rations de la file
*/

#include <malloc.h>
#include <assert.h>

#include "file.h"


void initialiser(File *f)
{
    // TODO
    f = malloc(sizeof(struct File));
    f->tete = NULL;
    f->queue = NULL;
    assert(est_vide(*f));
}


void detruire(File *f)
{
    // TODO
    free(f);
    f = NULL;
}


char tete(File f)
{
    assert(! est_vide(f));

    // TODO
    return f.tete->valeur;
}


bool est_vide(File f)
{
    // TODO
    return (f.tete == NULL && f.queue == NULL);
}

/**
 * Obtenir une nouvelle cellule allou�e dynamiquement
 * initialis�e avec la valeur et la cellule suivante pr�cis� en param�tre.
 */
static Cellule * cellule(char valeur, Cellule *suivante)
{
    // TODO
    Cellule *c;
    c = malloc(sizeof(struct Cellule));
    c->valeur = valeur;
    c->suivante = suivante;
}


void inserer(File *f, char v)
{
    assert(f != NULL);
  
    // TODO
    Cellule *nv = cellule(v, NULL);
    f->queue->suivante = nv;
    f->queue = f->queue->suivante;
}

void extraire(File *f, char *v)
{
    assert(f != NULL);
    assert(! est_vide(*f));

    // TODO
    if (f->tete == f->queue) {
	detruire(f);
    } else {
	f->tete = f->tete->suivante;
    }
}


int longueur(File f)
{
    // TODO
    int res = 0;
    if (f.tete == NULL) {
	return res;
    } else {
        File tmp = f;
	Cellule *c = f.tete;
	res++;
	while (c != tmp.queue) {
		res++;
		tmp.tete = c->suivante;	
	}
	return res;
    }
}
