# /usr/bin/env python3

import pandas as pd
import numpy as np
import six
import matplotlib.pyplot as plt

df = pd.read_csv("data/ag.csv", sep=";")

def render_mpl_table(data, name, col_width=3.0, row_height=0.625, font_size=14,
                     header_color='#40466e', row_colors=['#f1f1f2', 'w'], edge_color='w',
                     bbox=[0, 0, 1, 1], header_columns=0,
                     ax=None, **kwargs):
    if ax is None:
        size = (np.array(data.shape[::-1]) + np.array([0, 1])) * np.array([col_width, row_height])
        fig, ax = plt.subplots(figsize=size)
        ax.axis('off')
    mpl_table = ax.table(cellText=data.values, bbox=bbox, colLabels=data.columns, **kwargs)

    mpl_table.auto_set_font_size(False)
    mpl_table.set_fontsize(font_size)

    for k, cell in  six.iteritems(mpl_table._cells):
        cell.set_edgecolor(edge_color)
        if k[0] == 0 or k[1] < header_columns:
            cell.set_text_props(weight='bold', color='w')
            cell.set_facecolor(header_color)
        else:
            cell.set_facecolor(row_colors[k[0]%len(row_colors) ])
    fig.savefig(name)
    return ax

def nbIteration_distanceOpt(df):
    fig = plt.plot(df["nbIterationAlgo"], df["distanceOpt"])
    plt.xlabel("Nombre d'itération de l'algorithme")
    plt.ylabel("Distance optimale moyenne trouvée")
    plt.title("Distance moyenne en fonction du nombre d'itérations choisi")
    plt.show()


def nbIteration_tpsEnMs(df):
    fig = plt.plot(df["nbIterationAlgo"], df["tpsEnMs"])
    plt.xlabel("Nombre d'itération de l'algorithme")
    plt.ylabel("Temps d'exécution moyen de l'agorithme (ms)")
    plt.title("Temps d'exécution en fonction du nombre d'itérations choisi")
    plt.show()


def taillePVC_distanceOpt(df):
    fig = plt.plot(df["taillePVC"], df["distanceOpt"])
    plt.xlabel("Nombre de villes")
    plt.ylabel("Distance optimale moyenne trouvée")
    plt.title("Distance moyenne optimale en fonction du nombre de villes")
    plt.show()


def taillePVC_tpsEnMs(df):
    fig = plt.plot(df["taillePVC"], df["tpsEnMs"])
    plt.xlabel("Nombre de villes")
    plt.ylabel("Temps d'exécution moyen de l'agorithme (ms)")
    plt.title("Temps d'exécution en fonction du nombre de villes")
    plt.show()


def gen_table(df, n, filepath):
    df = df.where(df["nbIterationAlgo"] == n)
    df = df.drop("nbIterationAlgo", axis=1)
    df["moyenne"] = df["distanceOpt"]["mean"]
    df["ecart"] = ((df["distanceOpt"]["mean"] - df["distanceOpt"]
                    ["min"]) / df["distanceOpt"]["mean"]) * 100
    df["tps"] = df["tpsEnMs"]["mean"]
    df = df.drop("distanceOpt", axis=1)
    df = df.drop("tpsEnMs", axis=1)
    df.columns = df.columns.droplevel(1)
    df = df.rename(columns={"taillePVC": "Nombre de villes", "moyenne": "Moyenne", "ecart": "Écart", "tps": "Durée"})
    render_mpl_table(df, filepath)

def transform(df):
    df = df.groupby(["taillePVC", "nbIterationAlgo"], as_index=False)[
        ["distanceOpt", "tpsEnMs"]].agg({'distanceOpt': ['mean', 'min'], 'tpsEnMs': ['mean']})
    return df

df = transform(df)
gen_table(df, 100, "python/table.png")
"""
taillePVC_distanceOpt(df)
taillePVC_tpsEnMs(df)
nbIteration_distanceOpt(df)
nbIteration_tpsEnMs(df)
"""
