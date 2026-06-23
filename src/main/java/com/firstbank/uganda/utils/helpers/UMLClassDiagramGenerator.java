package com.firstbank.uganda.utils.helpers;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class UMLClassDiagramGenerator {

    public static void main(String[] args) {
        drawUMLClassDiagram();
    }

    public static void drawUMLClassDiagram() {
        // Image dimensions
        int width = 1100;
        int height = 800;
        Color backgroundColor = Color.WHITE;

        // Create image
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = image.createGraphics();

        // Set background
        g2d.setColor(backgroundColor);
        g2d.fillRect(0, 0, width, height);

        // Enable anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // Load fonts
        Font font = loadFont("Consolas", Font.PLAIN, 12);
        Font fontSmall = loadFont("Consolas", Font.PLAIN, 10);
        Font fontBold = loadFont("Consolas", Font.BOLD, 13);

        if (font == null) {
            font = new Font("Monospaced", Font.PLAIN, 12);
            fontSmall = new Font("Monospaced", Font.PLAIN, 10);
            fontBold = new Font("Monospaced", Font.BOLD, 13);
        }

        // Colors
        Color black = Color.BLACK;
        Color white = Color.WHITE;
        Color gray = new Color(200, 200, 200);
        Color lightGray = new Color(240, 240, 240);
        Color darkGray = new Color(100, 100, 100);

        // ===== MAIN ACCOUNT CLASS BOX =====
        int mainX = 200;
        int mainY = 50;
        int mainWidth = 450;
        int mainHeight = 280;

        // Main class box
        g2d.setColor(lightGray);
        g2d.fillRect(mainX, mainY, mainWidth, mainHeight);
        g2d.setColor(black);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawRect(mainX, mainY, mainWidth, mainHeight);

        // <<abstract>> label
        g2d.setColor(darkGray);
        g2d.setFont(font);
        g2d.drawString("<<abstract>>", mainX + mainWidth / 2 - 50, mainY + 20);

        // Class name
        g2d.setColor(black);
        g2d.setFont(fontBold);
        g2d.drawString("Account", mainX + mainWidth / 2 - 35, mainY + 42);

        // Separator line
        g2d.setColor(black);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(mainX, mainY + 55, mainX + mainWidth, mainY + 55);

        // Attributes
        List<String> attributes = Arrays.asList(
            "- accountNumber: String",
            "- customer: Customer",
            "- balance: double",
            "- openingDate: LocalDate",
            "- interestRate: double"
        );

        int yPos = mainY + 68;
        g2d.setFont(font);
        g2d.setColor(black);
        for (String attr : attributes) {
            g2d.drawString(attr, mainX + 10, yPos);
            yPos += 22;
        }

        // Separator line
        g2d.setColor(black);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(mainX, yPos, mainX + mainWidth, yPos);
        yPos += 10;

        // Methods
        List<String> methods = Arrays.asList(
            "+ Account(customer: Customer, deposit: double)",
            "+ abstract minimumDeposit(): double",
            "+ abstract getAccountType(): String",
            "+ abstract getFeatures(): String",
            "+ abstract hasOverdraft(): boolean",
            "+ abstract getInterestRate(): double",
            "+ generateAccountNumber(): String",
            "+ validateDeposit(deposit: double): boolean",
            "+ getAccountSummary(): String"
        );

        g2d.setFont(font);
        g2d.setColor(black);
        for (String method : methods) {
            g2d.drawString(method, mainX + 10, yPos);
            yPos += 22;
        }

        // ===== ARROWS AND SUBCLASSES =====
        int arrowYStart = mainY + mainHeight;
        int[] arrowXPositions = {mainX + 45, mainX + 135, mainX + 240, mainX + 335, mainX + 425};

        int subclassWidth = 140;
        int subclassHeight = 150;
        int subclassY = arrowYStart + 30;
        int[] subclassXPositions = {25, 175, 325, 475, 625};

        // Subclass data
        String[][] subclasses = {
            {"Savings\nAccount", "MIN:50,000", "Interest", "No Over-", "draft"},
            {"Current\nAccount", "MIN:200K", "Overdraft", "No Int", ""},
            {"FixedDep\nAccount", "MIN:1,000K", "Highest", "Locked", "Term"},
            {"Student\nAccount", "MIN:10K", "Age:18-25", "No Over-", "draft"},
            {"Joint\nAccount", "MIN:100,000", "Second NIN", "Shared", "Liability"}
        };

        g2d.setStroke(new BasicStroke(2));

        for (int i = 0; i < 5; i++) {
            int arrowX = arrowXPositions[i];
            int subX = subclassXPositions[i];

            // Draw arrow line (vertical)
            g2d.setColor(black);
            g2d.drawLine(arrowX, arrowYStart, arrowX, arrowYStart + 20);

            // Draw triangle arrowhead
            int[] xPoints = {arrowX - 6, arrowX + 6, arrowX};
            int[] yPoints = {arrowYStart + 20, arrowYStart + 20, arrowYStart + 28};
            g2d.fillPolygon(xPoints, yPoints, 3);
            g2d.drawPolygon(xPoints, yPoints, 3);

            // Draw subclass box
            g2d.setColor(white);
            g2d.fillRect(subX, subclassY, subclassWidth, subclassHeight);
            g2d.setColor(black);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawRect(subX, subclassY, subclassWidth, subclassHeight);

            // Subclass name (with line break)
            String[] nameLines = subclasses[i][0].split("\n");
            g2d.setColor(black);
            g2d.setFont(fontBold);

            if (nameLines.length == 1) {
                g2d.drawString(subclasses[i][0], subX + subclassWidth / 2 - 30, subclassY + 25);
            } else {
                g2d.drawString(nameLines[0], subX + subclassWidth / 2 - 35, subclassY + 18);
                g2d.drawString(nameLines[1], subX + subclassWidth / 2 - 35, subclassY + 38);
            }

            // Separator
            g2d.setColor(black);
            g2d.setStroke(new BasicStroke(2));
            g2d.drawLine(subX, subclassY + 50, subX + subclassWidth, subclassY + 50);

            // Details
            g2d.setFont(fontSmall);
            g2d.setColor(black);
            int detailY = subclassY + 62;
            for (int j = 1; j < subclasses[i].length; j++) {
                String detail = subclasses[i][j];
                if (!detail.isEmpty()) {
                    g2d.drawString(detail, subX + 10, detailY);
                    detailY += 20;
                }
            }

            // Connection line from arrow to subclass box
            g2d.setColor(black);
            g2d.setStroke(new BasicStroke(1));
            g2d.drawLine(arrowX, arrowYStart + 28, arrowX, subclassY);
        }

        // Clean up
        g2d.dispose();

        // Save the image
        try {
            String outputPath = "uml_class_diagram.png";
            ImageIO.write(image, "PNG", new File(outputPath));
            System.out.println("UML Class Diagram saved as: " + outputPath);
        } catch (IOException e) {
            System.err.println("Error saving image: " + e.getMessage());
        }
    }

    private static Font loadFont(String fontName, int style, int size) {
        try {
            return new Font(fontName, style, size);
        } catch (Exception e) {
            return null;
        }
    }
}