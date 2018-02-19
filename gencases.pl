#!/usr/bin/perl -w

use strict;

# I don't bother to use a cryptographically strong random number module,
# because this code isn't especially critical.

foreach (1 .. 10) {
    foreach (1 .. 12) {
        my $carry = 11;
        my $i = int(rand($carry));
        if ($i == 10) {
            print 'X ';
        } else {
            if ($i == 0) {
                print '-';
            } else {
                print $i;
            }
            $carry -= $i;
            $i = int(rand($carry));
            if ($i == $carry) {
                print '/';
            } elsif ($i == 0) {
                print '-';
            } else {
                print $i;
            }
        }
        print ' ';
    }
    print "\n";
}
