# /usr/bin/env python3

import pandas as pd
import numpy as np
import six
import matplotlib.pyplot as plt


def render_mpl_table(data, name, col_width=3.0, row_height=0.625, font_size=14,
                     header_color='#40466e', row_colors=['#f1f1f2', 'w'], edge_color='w',
                     bbox=[0, 0, 1, 1], header_columns=0,
                     ax=None, **kwargs):
    if ax is None:
        size = (np.array(data.shape[::-1]) + np.array([0, 1])
                ) * np.array([col_width, row_height])
        fig, ax = plt.subplots(figsize=size)
        ax.axis('off')
    mpl_table = ax.table(cellText=data.values, bbox=bbox,
                         colLabels=data.columns, **kwargs)

    mpl_table.auto_set_font_size(False)
    mpl_table.set_fontsize(font_size)

    for k, cell in six.iteritems(mpl_table._cells):
        cell.set_edgecolor(edge_color)
        if k[0] == 0 or k[1] < header_columns:
            cell.set_text_props(weight='bold', color='w')
            cell.set_facecolor(header_color)
        else:
            cell.set_facecolor(row_colors[k[0] % len(row_colors)])
    fig.savefig(name)
    plt.close()
    return ax


def nbIteration_distanceOpt(df, filename):
    df.drop("taillePVC", axis=1)
    df = df.groupby(["nbIterationAlgo"], as_index=False)[
        ["moyenne", "tps"]].agg({'moyenne': ['mean'], 'tps': ['mean']})
    df.columns = df.columns.droplevel(1)
    fig = plt.plot(df["nbIterationAlgo"], df["moyenne"])
    plt.xlabel("Nombre d'itération de l'algorithme")
    plt.ylabel("Distance optimale moyenne trouvée")
    plt.title("Distance moyenne en fonction du nombre d'itérations choisi")
    plt.savefig(filename)
    plt.close()


def nbIteration_tpsMs(df, filename):
    df.drop("taillePVC", axis=1)
    df = df.groupby(["nbIterationAlgo"], as_index=False)[["moyenne", "tps"]].agg({'moyenne': ['mean'], 'tps': ['mean']})
    df.columns = df.columns.droplevel(1)
    fig = plt.plot(df["nbIterationAlgo"], df["tps"])
    plt.xlabel("Nombre d'itération de l'algorithme")
    plt.ylabel("Temps d'exécution moyen de l'agorithme (ms)")
    plt.title("Temps d'exécution en fonction du nombre d'itérations choisi")
    plt.savefig(filename)
    plt.close()


def taillePVC_distanceOpt(df, filename):
    df.drop("nbIterationAlgo", axis=1)
    df = df.groupby(["taillePVC"], as_index=False)[["moyenne", "tps"]].agg(
        {'moyenne': ['mean'], 'tps': ['mean']})
    df.columns = df.columns.droplevel(1)
    fig = plt.plot(df["taillePVC"], df["moyenne"])
    plt.xlabel("Nombre de villes")
    plt.ylabel("Distance optimale moyenne trouvée")
    plt.title("Distance moyenne optimale en fonction du nombre de villes")
    plt.savefig(filename)
    plt.close()


def taillePVC_tpsMs(df, filename):
    df.drop("nbIterationAlgo", axis=1)
    df = df.groupby(["taillePVC"], as_index=False)[["moyenne", "tps"]].agg(
        {'moyenne': ['mean'], 'tps': ['mean']})
    df.columns = df.columns.droplevel(1)
    fig = plt.plot(df["taillePVC"], df["tps"])
    plt.xlabel("Nombre de villes")
    plt.ylabel("Temps d'exécution moyen de l'algorithme (ms)")
    plt.title("Temps d'exécution en fonction du nombre de villes")
    plt.savefig(filename)
    plt.close()

def distanceOpt_tpsMs(df,filename, n=100):
    plot_distanceOpt_tpsMs(df,n,filename)
    plt.savefig(filename)
    plt.close()

def plot_distanceOpt_tpsMs(df,n,algo):
    df = df.drop("nbIterationAlgo", axis=1)
    df = df.where(df["taillePVC"] == n)
    plt.plot(df["tps"], df["moyenne"], label=algo)
    plt.xlabel("Temps d'éxécution de l'algorithme (ms)")
    plt.ylabel("Distance optimale moyenne trouvée")
    plt.title(f"Distance optimale en fonction du temps d'exécution pour n={n} villes")
    plt.legend()
    
def gen_table(df, n, filepath):
    df = df.where(df["nbIterationAlgo"] == n)
    df = df.drop("nbIterationAlgo", axis=1)
    df = df.groupby(["taillePVC"], as_index=False)[["moyenne", "tps", "ecart"]].agg(
        {'moyenne': ['min'], 'tps': ['mean'],'ecart' : ['mean']})
    df.columns = df.columns.droplevel(1)
    df = df.rename(columns={"taillePVC": "Nombre de villes",
                            "moyenne": "Moyenne (km)", "ecart": "Écart (%)", "tps": "Durée (ms)"})
    df = df.applymap(lambda x: '{0:.2f}'.format(x))
    render_mpl_table(df, filepath)

def compare_algorithms(n):
    for filename in ["rs","ag","tabu"]:
        df = pd.read_csv("tests/data/" + filename + ".csv", sep=";")
        df = transform(df)
        plot_distanceOpt_tpsMs(df,n,filename)
    plt.savefig("tests/results/comp_" + str(n) + ".png")
    plt.close()
    

def transform(df):
    df = df.groupby(["taillePVC", "nbIterationAlgo"], as_index=False)[
        ["distanceOpt", "tpsMs"]].agg({'distanceOpt': ['mean', 'min'], 'tpsMs': ['mean']})
    df["moyenne"] = df["distanceOpt"]["mean"]
    df["ecart"] = ((df["distanceOpt"]["mean"] - df["distanceOpt"]
                    ["min"]) / df["distanceOpt"]["mean"]) * 100
    df["tps"] = df["tpsMs"]["mean"]
    df = df.drop("distanceOpt", axis=1)
    df = df.drop("tpsMs", axis=1)
    df.columns = df.columns.droplevel(1)

    return df


filename = "rs"
df = pd.read_csv("tests/data/" + filename + ".csv", sep=";")
df = transform(df)
for nb_iteration in range(1000, 50001, 1000):
    gen_table(df, nb_iteration, "tests/results/" +
              filename + "_" + str(nb_iteration) + ".png")
taillePVC_distanceOpt(df, "tests/results/" + filename + "_1.png")
taillePVC_tpsMs(df, "tests/results/" + filename + "_2.png")
nbIteration_distanceOpt(df, "tests/results/" + filename + "_3.png")
nbIteration_tpsMs(df, "tests/results/" + filename + "_4.png")
distanceOpt_tpsMs(df, "tests/results/" + filename + "_5.png")

filename = "ag"
df = pd.read_csv("tests/data/" + filename + ".csv", sep=";")
df = transform(df)
for nb_iteration in range(100, 1001, 100):
    gen_table(df, nb_iteration, "tests/results/" +
              filename + "_" + str(nb_iteration) + ".png")
taillePVC_distanceOpt(df, "tests/results/" + filename + "_1.png")
taillePVC_tpsMs(df, "tests/results/" + filename + "_2.png")
nbIteration_distanceOpt(df, "tests/results/" + filename + "_3.png")
nbIteration_tpsMs(df, "tests/results/" + filename + "_4.png")
distanceOpt_tpsMs(df, "tests/results/" + filename + "_5.png")

filename = "tabu"
df = pd.read_csv("tests/data/" + filename + ".csv", sep=";")
df = transform(df)
for nb_iteration in range(50, 201, 10):    
    gen_table(df, nb_iteration, "tests/results/" +
              filename + "_" + str(nb_iteration) + ".png")
taillePVC_distanceOpt(df, "tests/results/" + filename + "_1.png")
taillePVC_tpsMs(df, "tests/results/" + filename + "_2.png")
nbIteration_distanceOpt(df, "tests/results/" + filename + "_3.png")
nbIteration_tpsMs(df, "tests/results/" + filename + "_4.png")
distanceOpt_tpsMs(df, "tests/results/" + filename + "_5.png")

for n in range(10,101,10):
    compare_algorithms(n)