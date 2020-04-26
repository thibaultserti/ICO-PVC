#!/usr/bin/env/ python3

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


def transform(df, type_interaction):
    df = df.where(df[0] == type_interaction)
    df = df.rename(columns={0: "Type d'interaction",
                            1: "Nombre de villes", 2: "Moyenne (km)", 3: "Durée (ms)"})
    header = {"Type d'interaction": [],
         "Nombre de villes": [], "Moyenne (km)": [], "Durée (ms)": []}
    d = pd.DataFrame(header)
    for index, row in df.iterrows():
        if index % 3 == 0:
            dist_min = float("inf")
            temps_max = 0
        if row["Moyenne (km)"] < dist_min:
            dist_min = row["Moyenne (km)"]
        if row["Durée (ms)"] > temps_max:
            temps_max += row["Durée (ms)"]
        if index % 3 == 2:
            r = {"Type d'interaction": row["Type d'interaction"], "Nombre de villes": row["Nombre de villes"],
             "Moyenne (km)": dist_min, "Durée (ms)": temps_max/3}
            d = d.append(r,ignore_index=True)
    return d

def plot_distanceOpt_tpsMs(df,type_interaction,n):
    df = df.where(df["Nombre de villes"] == n)
    df = df.sort_values(by=["Durée (ms)"])
    plt.plot(df["Durée (ms)"], df["Moyenne (km)"], label=type_interaction)
    plt.xlabel("Temps d'éxécution de l'algorithme (ms)")
    plt.ylabel("Distance optimale moyenne trouvée")
    plt.title(f"Distance optimale en fonction du temps d'exécution pour n={n} villes")
    plt.legend()

def distanceOpt_tpsMs(df,type_interaction, n):
    plot_distanceOpt_tpsMs(df,type_interaction,n)
    plt.savefig("tests/results/sma_" + type_interaction + str(n))
    plt.close()

def compare_algorithms(n):
    for type_interaction in ["collaboration", "competition"]:
        df = pd.read_csv("tests/data/sma.csv", sep=";", header=None)
        df = transform(df, type_interaction)
        plot_distanceOpt_tpsMs(df,type_interaction,n)
    plt.savefig("tests/results/comp_sma" + str(n) + ".png")
    plt.close()

def gen_table(df, filepath):
    df = df.drop("Type d'interaction", axis=1)
    df = df.groupby(["Nombre de villes"], as_index=False)[["Moyenne (km)", "Durée (ms)"]].agg(
        {'Moyenne (km)': ['min'], 'Durée (ms)': ['mean']})
    df.columns = df.columns.droplevel(1)
    df = df.applymap(lambda x: '{0:.2f}'.format(x))
    render_mpl_table(df, filepath)

for type_interaction in ["competition", "collaboration"]:
    df = pd.read_csv("tests/data/sma.csv", sep=";", header=None)
    df = transform(df, type_interaction)
    gen_table(df, "tests/results/" + type_interaction + ".png")
    for n in range(10,201,10):
        distanceOpt_tpsMs(df,type_interaction, n)

for n in range(10,201,10):
    compare_algorithms(n)