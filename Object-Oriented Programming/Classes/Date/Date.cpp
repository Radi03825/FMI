#include "Date.h"
#include <exception>

namespace {
    const size_t DEFAULT_DAY = 1;
    const size_t DEFAULT_MONTH = 1;
    const size_t DEFAULT_YEAR = 2000;
}

Date::Date() {
    this->day = DEFAULT_DAY;
    this->month = DEFAULT_MONTH;
    this->year = DEFAULT_YEAR;
}

Date::Date(size_t day, size_t month, size_t year) {
    this->setYear(year);
    this->setMonth(month);
    this->setDay(day);
}

Date::Date(const Date& other) {
    this->setYear(other.year);
    this->setMonth(other.month);
    this->setDay(other.day);
}

Date& Date::operator=(const Date& other) {
    if (this != &other) {
        this->setYear(other.year);
        this->setMonth(other.month);
        this->setDay(other.day);
    }

    return *this;
}

void Date::setDay(const size_t day) {
    if (day < 1 || day > this->getDaysCountOfMonth(this->month, this->year)) {
        throw std::logic_error("Invalid day");
    }

    this->day = day;
}

void Date::setMonth(const size_t month) {
    this->month = month;
}

void Date::setYear(const size_t year) {
    this->year = year;
}

size_t Date::getDay() const {
    return this->day;
}

size_t Date::getMonth() const {
    return this->month;
}

size_t Date::getYear() const {
    return this->year;
}

void Date::addDays(const size_t days) {
    this->day += days;

    if (this->day > this->getDaysCountOfMonth(this->month, this->year)) {
        this->day -= this->getDaysCountOfMonth(this->month, this->year);
        this->month++;

        if (this->month > 12) {
            this->year++;
            this->month = 1;
        }
    }
}

void Date::removeDays(const size_t days) {
    this->day -= days;

    if (this->day < 1) {
        this->month--;

        if (this->month < 1) {
            this->year--;
            this->month = 12;
        }

        this->day = this->getDaysCountOfMonth(this->month, this->year) + this->day;
    }
}

bool Date::isLeapYear() const {
    return this->isLeapYear(this->year);
}

size_t Date::daysToXmas() const {
    size_t currentYear = this->year;

    if (this->month == 12 && this->day > 25) {
        currentYear++;
    }

    Date nextXmas(25, 12, currentYear);

    return this->daysBetweenDates(nextXmas);
}

size_t Date::daysToNewYear() const {
    Date nextNewYear(1, 1, this->year + 1);

    return this->daysBetweenDates(nextNewYear);
}

int Date::daysBetweenDates(const Date& other) const {
    Date early;
    Date later;

    if (this->isLaterThan(other)) {
        early = other;
        later = *this;
    } else if (other.isLaterThan(*this)) {
        early = *this;
        later = other;
    } else {
        return 0;
    }

    int counter = 0;

    while (!early.isEqualTo(later)) { 
        if (early.year == later.year && early.month == later.month) {
            counter += later.day - early.day;
            break;
        } else if (counter == 0) {
            counter += this->getDaysUntilTheEndOfTheMonth(early.day, early.month, early.year) + 1;
            early += counter;

            if (early.year == later.year && early.month == later.month) {
                counter += later.day - early.day;
                break;
            }
        }

        int monthDaysCount = getDaysCountOfMonth(early.month, early.year);

        counter += monthDaysCount;
        early += monthDaysCount;
    }

    return counter - 1;
}

bool Date::isLaterThan(const Date& other) const {
    /*if (this->year > other.year) {
        return true;
    } else if (this->year < other.year) {
        return false;
    } else {
        if (this->month > other.month) {
            return true;
        } else if (this->month < other.month) {
            return false;
        } else {
            return (this->day > other.day);
        }
    }*/

    return (this->year > other.year) ? true
         : (this->year < other.year) ? false
         : (this->month > other.month) ? true
         : (this->month < other.month) ? false
         : (this->day > other.day);
}

bool Date::isEqualTo(const Date& other) const {
    return (this->year == other.year) && (this->month == other.month) && (this->day == other.day);
}

int Date::getDaysUntilTheEndOfTheMonth() const {
    return this->getDaysUntilTheEndOfTheMonth(this->day, this->month, this->year);
}

void Date::print() const {
    if (this->day < 10) {
        std::cout << "0" << this->day << "/";
    } else {
        std::cout << this->day << "/";
    }

    if (this->month < 10) {
        std::cout << "0" << this->month << "/";
    } else {
        std::cout << this->month << "/";
    }

    std::cout << this->year << std::endl;
}

Date& Date::operator+=(const int days) {
    this->addDays(days);

    return *this;
}

Date& Date::operator-=(const int days) {
    this->removeDays(days);

    return *this;
}

Date& Date::operator++() {
    *this += 1;

    return *this;
}

Date& Date::operator--() {
    *this -= 1;
    
    return *this;
}

Date& Date::operator++(const int) {
    *this += 1;

    return *this;
}

Date& Date::operator--(const int) {
    *this -= 1;

    return *this;
}

bool operator==(const Date& lhs, const Date& rhs) {
    return (lhs.year == rhs.year) && (lhs.month == rhs.month) && (lhs.day == rhs.day);
}

bool operator!=(const Date& lhs, const Date& rhs) {
    return !(lhs == rhs);
}

bool Date::isLeapYear(const int year) const {
    return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
}

int Date::getDaysUntilTheEndOfTheMonth(const int day, const int month, const int year) const {
    int currentMonthDaysCount = this->getDaysCountOfMonth(this->month, this->year);

    return (this->getDaysCountOfMonth(this->month, this->year) - day);
}

size_t Date::getDaysCountOfMonth(const int month, const int year) const {

    switch (month) {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
            return 31;
        case 4:
        case 6:
        case 9:
        case 11:
            return 30;
        case 2:
            return isLeapYear(year) ? 29 : 28;
        default:
            throw std::logic_error("Invalid month");
    }

}

std::ostream& operator<<(std::ostream& os, const Date& date) {
    if (date.day < 10) {
        os << "0";
    }
    os << date.day << "/";

    if (date.month < 10) {
        os << "0";
    }
    os << date.month << "/" << date.year;

    return os;
}

std::istream& operator>>(std::istream& is, Date& date) {
    is >> date.day >> date.month >> date.year;

    return is;
}