# vychozi obrazky
SRC=  Zobrazenie.jpg NahratieSuboru.jpg
# pozadovany format pro LaTeX
DEST= NahratieSuboru.eps Zobrazenie.eps

.SUFFIXES: .fig .eps .png .jpg

all: $(DEST)

.fig.eps:
	fig2dev -L eps $< $@

.png.eps:
	convert $< $@

.jpg.eps:
	convert $< $@
