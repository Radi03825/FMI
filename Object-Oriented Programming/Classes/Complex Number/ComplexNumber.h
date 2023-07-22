#pragma once
#include <iostream>

class ComplexNumber {
    private:
        double real;
        double imaginary;

    public:
        ComplexNumber();
        ComplexNumber(const double, const double);
        ComplexNumber(const ComplexNumber&);

        ComplexNumber& operator=(const ComplexNumber&);

        ~ComplexNumber() = default;

        void setReal(const double);
        void setImaginary(const double);

        double getReal() const;
        double getImaginary() const;

        bool isEqual(const ComplexNumber) const;
        ComplexNumber& add(const ComplexNumber);
        ComplexNumber& subtract(const ComplexNumber);
        ComplexNumber& multiply(const ComplexNumber);
        void conjugate(const ComplexNumber);
        ComplexNumber getConjugated() const;
        void print() const;

        ComplexNumber& operator+=(const ComplexNumber&);
        ComplexNumber& operator-=(const ComplexNumber&);
        ComplexNumber& operator*=(const ComplexNumber&);
        ComplexNumber& operator/=(const ComplexNumber&);

        friend bool operator==(const ComplexNumber&, const ComplexNumber&);
        friend bool operator!=(const ComplexNumber&, const ComplexNumber&);

        friend ComplexNumber operator+(const ComplexNumber&, const ComplexNumber&);
        friend ComplexNumber operator-(const ComplexNumber&, const ComplexNumber&);
        friend ComplexNumber operator*(const ComplexNumber&, const ComplexNumber&);
        friend ComplexNumber operator/(const ComplexNumber&, const ComplexNumber&);

        friend std::ostream& operator<<(std::ostream&, const ComplexNumber&);
        friend std::istream& operator>>(std::istream&, ComplexNumber&);
};