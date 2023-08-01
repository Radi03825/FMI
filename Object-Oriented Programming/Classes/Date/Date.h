#pragma once
#include <iostream>

class Date {

    private:
        size_t day;
        size_t month;
        size_t year;

    public:
        Date(); //Default to 01.01.2000
        Date(const size_t, const size_t, const size_t);
        Date(const Date&);

        Date& operator=(const Date&);

        size_t getDay() const;
        size_t getMonth() const;
        size_t getYear() const;

        void addDays(const size_t);
        void removeDays(const size_t);
        bool isLeapYear() const;
        size_t daysToXmas() const;
        size_t daysToNewYear() const;
        int daysBetweenDates(const Date&) const;
        bool isEqualTo(const Date&) const;
        bool isLaterThan(const Date&) const;

        int getDaysUntilTheEndOfTheMonth() const;

        void print() const;

        Date& operator+=(const int);
        Date& operator-=(const int);

        Date& operator++();
        Date& operator--();

        Date& operator++(const int);
        Date& operator--(const int);

        friend bool operator==(const Date&, const Date&);
        friend bool operator!=(const Date&, const Date&);

        friend std::ostream& operator<<(std::ostream&, const Date&);
        friend std::istream& operator>>(std::istream&, Date&);

    private:
        void setDay(const size_t);
        void setMonth(const size_t);
        void setYear(const size_t);

        bool isLeapYear(const int) const;
        int getDaysUntilTheEndOfTheMonth(const int, const int, const int) const;

        size_t getDaysCountOfMonth(const int, const int) const;
};