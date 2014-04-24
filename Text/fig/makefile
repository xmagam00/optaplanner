# vychozi obrazky
SRC=  Zobrazenie.jpg NahratieSuboru.jpg seCaseOveriew.png ER.jpg UserCase.jpg webtechnology.jpg
# pozadovany format pro LaTeX
DEST= NahratieSuboru.eps Zobrazenie.eps useCaseOverview.eps UseCase.eps ER.eps webtechnology.eps

.SUFFIXES: .fig .eps .png .jpg

all: $(DEST)

.fig.eps:
	fig2dev -L eps $< $@

.png.eps:
	convert $< $@

.jpg.eps:
	convert $< $@
