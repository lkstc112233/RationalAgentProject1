#!/bin/bash

for filename in ./*.txt; do
    if [[ $filename =~ graph([0-9]*)_[0-9]*\.txt ]]; then
        echo Handling: $filename
        for i in $(eval echo {0..$2}); do
            $1 $filename $((0 + RANDOM % ${BASH_REMATCH[1]})) $((0 + RANDOM % ${BASH_REMATCH[1]}))
        done
    fi
done