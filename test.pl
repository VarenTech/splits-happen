#!/usr/bin/perl -w

use SplitsHappen;
use strict;

my %tests = (
    # Make sure it works on the provided samples.
    'XXXXXXXXXXXX'          => 300,
    '9-9-9-9-9-9-9-9-9-9-'  => 90,
    '5/5/5/5/5/5/5/5/5/5/5' => 150,
    'X7/9-X-88/-6XXX81'     => 167,
    # Here are some randomly generated but eye-checked to be valid test cases.
    # I massaged them lightly to make the 10th frame a little more interesting.
    '13522122X142736X42'    => 78,
    '63617224369/31113/2/6' => 87,
    '532452637281X7/5127'   => 98,
    '9/714/X52217/519/8/7'  => 128,
    '31X9/273231XX9/XX3'    => 146,
    '723151177/3371X9/X8/'  => 114,
    '445454338/5345637/9/3' => 105,
    '2/8/4/33277136545/9/9' => 124,
    '45269/7/X9/9/149/X15'  => 145,
    'X9/617216638/811645'   => 111,
);

foreach my $k (sort keys %tests) {
    my $score = SplitsHappen::score($k);
    print "$k: expected: $tests{$k}, got: $score ... ";
    if ($score == $tests{$k}) {
        print "ok\n";
    } else {
        die "ERROR\n";
    }
}
