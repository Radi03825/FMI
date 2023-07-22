#include "ComplexNumber.h"

ComplexNumber::ComplexNumber() {
    this->real = 0;
    this->imaginary = 0;
}

ComplexNumber::ComplexNumber(const double real, const double imaginary) {
    this->setReal(real);
    this->setImaginary(imaginary);
}

ComplexNumber::ComplexNumber(const ComplexNumber& other) {
    this->setReal(other.real);
    this->setImaginary(other.imaginary);
}

ComplexNumber& ComplexNumber::operator=(const ComplexNumber& other) {
    this->setReal(other.real);
    this->setImaginary(other.imaginary);

    return *this;
}

void ComplexNumber::setReal(const double real) {
    this->real = real;
}

void ComplexNumber::setImaginary(const double imaginary) {
    this->imaginary = imaginary;
}

double ComplexNumber::getReal() const {
    return this->real;
}

double ComplexNumber::getImaginary() const {
    return this->imaginary;
}

bool ComplexNumber::isEqual(const ComplexNumber other) const {
    return (this->real == other.real && this->imaginary == other.imaginary);
}

ComplexNumber& ComplexNumber::add(const ComplexNumber other) {
    this->real += other.real;
    this->imaginary += other.imaginary;

    return *this;
}

ComplexNumber& ComplexNumber::subtract(const ComplexNumber other) {
    this->real -= other.real;
    this->imaginary -= other.imaginary;

    return *this;
}

ComplexNumber& ComplexNumber::multiply(const ComplexNumber other) {
    *this *= other;

    return *this;
}

void ComplexNumber::conjugate(const ComplexNumber other) {
    this->imaginary *= -1;
}

ComplexNumber ComplexNumber::getConjugated() const {
    ComplexNumber result(*this);
    result.imaginary *= -1;

    return result;
}

void ComplexNumber::print() const {
    std::cout << this->real;

    if (this->imaginary < 0) {
        std::cout << " - " << this->imaginary * (-1) << "i" << std::endl;
    } else {
        std::cout << " + " << this->imaginary << "i" << std::endl;
    }
}

ComplexNumber& ComplexNumber::operator+=(const ComplexNumber& other) {
    this->real += other.real;
    this->imaginary += other.imaginary;

    return *this;
}

ComplexNumber& ComplexNumber::operator-=(const ComplexNumber& other) {
    this->real -= other.real;
    this->imaginary -= other.imaginary;

    return *this;
}

ComplexNumber& ComplexNumber::operator*=(const ComplexNumber& other) {
    double oldReal = this->real;
    this->real = oldReal * other.real - this->imaginary * other.imaginary;
    this->imaginary = oldReal * other.imaginary + this->imaginary * other.real;

    return *this;
}

ComplexNumber& ComplexNumber::operator/=(const ComplexNumber& other) {
    ComplexNumber conjugated = other.getConjugated();

    ComplexNumber complexNumber(other);

    *this *= conjugated;
    complexNumber *= conjugated;

    if (complexNumber.real != 0) {
        this->real /= complexNumber.real;
        this->imaginary /= complexNumber.real;
    }

    return *this;
}

bool operator==(const ComplexNumber& lhs, const ComplexNumber& rhs) {
    return (lhs.real == rhs.real && lhs.imaginary == rhs.imaginary);
}

bool operator!=(const ComplexNumber& lhs, const ComplexNumber& rhs) {
    return !(lhs == rhs);
}

ComplexNumber operator+(const ComplexNumber& lhs, const ComplexNumber& rhs) {
    ComplexNumber result(lhs);

    result += rhs;

    return result;
}

ComplexNumber operator-(const ComplexNumber& lhs, const ComplexNumber& rhs) {
    ComplexNumber result(lhs);

    result -= rhs;

    return result;
}

ComplexNumber operator*(const ComplexNumber& lhs, const ComplexNumber& rhs) {
    ComplexNumber result(lhs);

    result *= rhs;

    return result;
}

ComplexNumber operator/(const ComplexNumber& lhs, const ComplexNumber& rhs) {
    ComplexNumber result(lhs);

    result /= rhs;

    return result;
}

std::ostream& operator<<(std::ostream& os, const ComplexNumber& complexNumber) {
    if (complexNumber.imaginary < 0) {
        os << complexNumber.real << " - " << complexNumber.imaginary * (-1) << "i";
    } else if (complexNumber.imaginary > 0) {
        os << complexNumber.real << " + " << complexNumber.imaginary << "i";
    } else {
        os << complexNumber.real;
    }

    return os;
}

std::istream& operator>>(std::istream& is, ComplexNumber& complexNumber) {
    is >> complexNumber.real >> complexNumber.imaginary;
    return is;
}