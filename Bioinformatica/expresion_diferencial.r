library(TCGAbiolinks)
library(edgeR)
library(gprofiler2)
library(limma)
library(gplots)
library(RColorBrewer)
library(EnhancedVolcano)

samples <- c(
    "TCGA-66-2768-01A-01R-0851-07",
    "TCGA-66-2770-01A-01R-0851-07",
    "TCGA-51-6867-01A-11R-2045-07",
    "TCGA-34-A5IX-01A-12R-A27Q-07",
    "TCGA-77-7138-01A-41R-2045-07",
    "TCGA-43-2578-01A-01R-0851-07",
    "TCGA-77-8131-01A-11R-2247-07",
    "TCGA-22-1000-01A-01R-A32Z-07",
    "TCGA-34-5929-01A-11R-1820-07",
    "TCGA-56-7221-01A-11R-2045-07",
    "TCGA-68-8250-01A-11R-2296-07",
    "TCGA-39-5024-01A-21R-1820-07",
    "TCGA-18-4086-01A-01R-1100-07",
    "TCGA-37-A5EN-01A-21R-A26W-07",
    "TCGA-85-7697-01A-11R-2125-07",
    "TCGA-63-5128-01A-01R-1443-07",
    "TCGA-33-4566-01A-01R-1443-07",
    "TCGA-NC-A5HR-01A-21R-A26W-07",
    "TCGA-NK-A5CT-01A-31R-A26W-07",
    "TCGA-77-8154-01A-11R-2247-07",
    "TCGA-34-8454-11A-01R-2326-07",
    "TCGA-58-8386-11A-01R-2296-07",
    "TCGA-77-8008-11A-01R-2187-07",
    "TCGA-22-5471-11A-01R-1635-07",
    "TCGA-22-5489-11A-01R-1635-07",
    "TCGA-56-8201-11A-01R-2247-07",
    "TCGA-56-8083-11A-01R-2247-07",
    "TCGA-56-8309-11A-01R-2296-07",
    "TCGA-56-7730-11A-01R-2125-07",
    "TCGA-22-5491-11A-01R-1858-07",
    "TCGA-43-6773-11A-01R-1949-07",
    "TCGA-56-8082-11A-01R-2247-07",
    "TCGA-33-4587-11A-01R-2125-07",
    "TCGA-43-6647-11A-01R-1820-07",
    "TCGA-85-7710-11A-01R-2125-07",
    "TCGA-43-7657-11A-01R-2125-07",
    "TCGA-77-7338-11A-01R-2045-07",
    "TCGA-22-5478-11A-11R-1635-07",
    "TCGA-60-2709-11A-01R-1820-07",
    "TCGA-56-7582-11A-01R-2045-07"
);

getWd()

query <- GDCquery(
    project = "TCGA-LUSC",
    data.category = "Transcriptome Profiling",
    data.type = "Gene Expression Quantification",
    workflow.type = "STAR - Counts",
    barcode = samples
)

GDCdownload(query)

raw.counts <- GDCprepare(query = query)

counts <- raw.counts@assays@data@listData$unstranded
colnames(counts) <- colnames(raw.counts)

ensg<- unlist(strsplit(rownames(raw.counts), split = "[.]"))
ensg<- ensg[c(TRUE, FALSE)]

gconvert <- gconvert(query=ensg, target="HGNC", mthreshold=1, filter_na=FALSE)
class(gconvert[,1])<-"integer"
gconvert<- gconvert[sort.list(gconvert[,1]),]
genesymbol <- gconvert[,5]
genesymbol<-ifelse(is.na(genesymbol), ensg, genesymbol)

rownames(counts) <- genesymbol

targets <- data.frame(sampleNames = colnames(counts), Group = rep(c("Tumor", "Normal"),  each = 20))

mycpm <- cpm(counts)

plot(counts[,1],mycpm[,1],xlim=c(0,20),ylim=c(0,0.5))
abline(v=10,col=2)
abline(h=0.15,col=4)

thresh <- mycpm > 0.15
keep <- rowSums(thresh) >= 3
table(keep)

counts.keep <- counts[keep,]
dim(counts.keep)

y <- DGEList(counts.keep)

barplot(y$samples$lib.size)

logcpm <- cpm(y$counts,log=TRUE)

group.col <- rep(c("red","blue"), each = 20)
boxplot(logcpm, xlab="", ylab="Log2 counts per million",las=2,col=group.col,
        pars=list(cex.lab=0.8,cex.axis=0.8))
abline(h=median(logcpm),col="blue")
title("Boxplots of logCPMs\n(unnormalised, coloured by groups)",cex.main=0.8)

plotMDS(y,col=group.col)
legend("topright", legend = levels(as.factor(targets$Group)), fill=c("red","blue"))

y <- calcNormFactors(y)
y$samples

design <- matrix(rep(0, 2*length(targets$Group)), ncol = length(unique(targets$Group)))
colnames(design) <- c('Tumor','Normal')
rownames(design) <- targets$sampleNames
design[which(targets$Group == "Tumor"),1]=1
design[which(targets$Group == "Normal"),2]=1

cont.matrix = makeContrasts('Tumor - Normal', levels = design)

par(mfrow=c(1,1))
v <- voom(y,design,plot=TRUE)

fit <- lmFit(v,design)
fit2 = contrasts.fit(fit, cont.matrix)
fit2 = eBayes(fit2)

results <- decideTests(fit2)
summary(results)

topTable(fit2, coef = 1, sort.by = "p")

tt<-topTable(fit2, coef = 1, adjust="fdr", number=Inf)

par(mfrow=c(1,1))
EnhancedVolcano(
    tt, 
	lab = tt$ID,
	x = "logFC",
	y = "adj.P.Val",
	pCutoff = 0.05,
	FCcutoff = 2,
	ylab = bquote(~-Log[10]~adjusted~italic(P)),
	title= "Differential Expression results"
)

gene_list <- topTable(fit2, coef=1, number=dim(fit2)[1], sort.by="logFC")
selected <- gene_list[which(gene_list$adj.P.Val<0.05 & abs(gene_list$logFC)>=2),]
HM_matrix <- counts[which(rownames(counts) %in% selected$ID),]

cases_colors <- rep(c("red", "blue"), each = 20)

par(mar=c(7,4,4,2)+0.1)

heatmap.2(
    HM_matrix, 
    col=greenred(75),
    scale="row",
    key=TRUE,
    symkey=FALSE,
    density.info="none",
    trace="none",
    cexRow=0.005,
    ColSideColors = cases_colors,
    margins=c(12,8)
)

write.csv(selected, file = "DE_genes_results.csv", quote = FALSE, row.names = FALSE)
write.csv(gene_list, file = "full_DE_genes_results.csv", quote = FALSE, row.names = FALSE)