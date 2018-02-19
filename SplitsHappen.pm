package SplitsHappen;

$| = 1;

=head1 NAME

SplitsHappen - A bowling scores calculator

=head1 SYNOPSIS

 use SplitsHappen;
 
 my $score = $SplitsHappen::score($line);
 print "Score: $score\n";

=head1 DESCRIPTION

SplitsHappen calculates American Ten-Pin Bowling poling scores for a single
game.

This package exports no methods by default, but implements C<score> as a
package method.

=head2 Scoring Logic

=over

=item * Each game, or "line" of bowling, includes ten turns, or "frames" for
the bowler.

=over

=item I<N.B.>: As per the specification, this package does not check for valid
rolls nor the correct number of rolls/frames.

=back

=item * In each frame, the bowler gets up to two tries to knock down all the
pins.

=item * If in two tries, he fails to knock them all down, his score for that
frame is the total number of pins knocked down in his two tries.

=item * If in two tries he knocks them all down, this is called a "spare" and
his score for the frame is ten plus the number of pins knocked down on his next
throw (in his next turn).

=item * If on his first try in the frame he knocks down all the pins, this is
called a "strike". His turn is over, and his score for the frame is ten plus
the simple total of the pins knocked down in his next two rolls.

=item * If he gets a spare or strike in the last (tenth) frame, the bowler gets
to throw one or two more bonus balls, respectively. These bonus throws are
taken as part of the same turn. If the bonus throws knock down all the pins,
the process does not repeat: the bonus throws are only used to calculate the
score of the final frame.

=item * The game score is the total of all frame scores.

=back

=head2 Syntax

=over

=item * [1-9]

The score for a single throw.

=item * X

A strike, as above.

=item * /

A spare, as above.

=item * -

No pins knocked down.

=back

=head2 Example Inputs

=over

=item XXXXXXXXXXXX

Scores 300: a strike on each frame, and 2 more strikes as the final bonus rolls.

=item 9-9-9-9-9-9-9-9-9-9- 

Scores 90, 9 points on each frame.

=item 5/5/5/5/5/5/5/5/5/5/5

Scores 150: a spare on each frame, and 5 more pins on the bonus roll.

=item X7/9-X-88/-6XXX81

Scores 167 (further explanation is in the accompanying F<README.md>).

=back

=head1 METHODS

=cut

use Exporter;
use strict;
use warnings;
use vars qw(@ISA $VERSION @EXPORT @EXPORT_OK $VERSION);

@ISA = qw(Exporter);
@EXPORT = qw();
@EXPORT_OK = qw(score);
$VERSION = "1.0";

# private method - returns the value of the given character
# X returns 10
# / returns -10
# [1-9] returns the digit
# - returns 0
# undefined otherwise

sub valueof($) {
    my $char = shift;
    my $rv;

    if ($char eq 'X') {
        $rv = 10;
    } elsif ($char eq '/') {
        $rv = -10;
    } elsif ($char eq '-') {
        $rv = 0;
    } elsif ($char =~ /[0-9]/) {
        $rv = $char;
    }
    return $rv;
}

=over

=item C<score>

Parses a scoring line, as described above, and returns the score as a number.
Results for invalid input are not specified.

=cut

sub score($) {
    my $line = shift;
    my @chars = split //, $line;

    print "Got line: $line with " . (scalar @chars) . " characters\n" if 0;
    my ($ofs, $i, $runtot, $val);
    my ($score, $frameno, $pinsleft) = (0, 0, 0);

    for ($ofs = 0; $frameno < 10; $ofs++) {
        if ($pinsleft == 0) {
            $frameno++;
            print "\nStarting new frame $frameno...\n" if 0;
            $pinsleft = 10;
        }
        $val = valueof($chars[$ofs]);
        print "Got value: $val\n" if 0;

        # We're not supposed to get a spare on the first frame, though a strike
        # is possible.
        print "Adding $val to $score...\n" if 0;
        $score += $val;
        $pinsleft -= $val;
        print "Score is now: $score ($pinsleft pins left)\n" if 0;
        if ($val == 10) {
            # It was a strike.  Add the next two rolls, unless this was a bonus
            # roll.
            print "STRIKE: Adding " . $chars[$ofs + 1] . " and " . $chars[$ofs + 2] . " to $score\n" if 0;
            $score += valueof($chars[$ofs + 1]);
            # Again, can't get a spare on the first roll of a frame, but can on
            # the second.
            $val = valueof($chars[$ofs + 2]);
            if ($val < 0) {
                $val = 10 - valueof($chars[$ofs + 1]);
            }
            $score += $val;
        }
        if ($pinsleft > 0) {
            print "Pins left, looking at next character.\n" if 0;
            $ofs++;
            # assuming valid input, so not checking defined status
            print "Checking out chars[$ofs]: " if 0;
            print "$chars[$ofs]\n" if 0;
            $val = valueof($chars[$ofs]);
            print "Got value: $val\n" if 0;
            # You can't get a strike on the second roll, but you can get a
            # spare.
            if ($val < 0) {
                print "SPARE: adding $pinsleft to $score...\n" if 0;
                $score += $pinsleft;
                print "Score is now: $score\n" if 0;
                print "Trying to look ahead on frame $frameno...\n" if 0;
                $score += valueof($chars[$ofs + 1]);
                print "BONUS: " . $chars[$ofs + 1] . "; score is now: $score\n" if 0;
            } else {
                print "Trying to add $val to $score...\n" if 0;
                $score += $val;
                print "Score is now: $score\n" if 0;
            }
            # They're out of rolls, clear the pins.
            $pinsleft = 0;
        }
        # frame is now over and all bonuses have been added
    }

    return $score;
}

=back

=head1 LICENCE

Copyright (c) 2018, Ben Stern
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:

=over

=item * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.

=item * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.

=item * Neither the name of the copyright holder nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.

=back

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

=head1 AUTHORS

Initial specification by Varen Technologies.  Implementation by Ben Stern L<bas-github@bstern.org>.

=cut

1;
