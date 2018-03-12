"""This script is a unit test of the bowling script"""

import unittest
from bowling import Bowling

class BowlingTest(unittest.TestCase):
    ''' Unit Test BowlingTest '''

    def test_one(self):
        ''' Test the perfect bowling session '''
        bowling_test = Bowling("XXXXXXXXXXXX")
        self.assertEqual(bowling_test.show_score(), 300)

    def test_two(self):
        ''' Test two '''
        bowling_test = Bowling("9-9-9-9-9-9-9-9-9-9-")
        self.assertEqual(bowling_test.show_score(), 90)

    def test_three(self):
        ''' test three '''
        bowling_test = Bowling("5/5/5/5/5/5/5/5/5/5/5")
        self.assertEqual(bowling_test.show_score(), 150)

    def test_four(self):
        ''' test four '''
        bowling_test = Bowling("X7/9-X-88/-6XXX81")
        self.assertEqual(bowling_test.show_score(), 167)

def main():
    ''' The main call for unit test '''
    unittest.main()

if __name__ == "__main__":
    main()
