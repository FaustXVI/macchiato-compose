# How to contribute

Contributions are *more* than welcome.

Please file bug reports and feature requests to https://github.com/vdemeester/macchiato-compose/issues.

# Making changes

- Fork the repository on Github
- Create a topic branch from where you want to base your work (usually the master branch)
- Check the formatting rules from existing code (no trailing whitepace, mostly default indentation)
- Ensure any new code is well-tested, and if possible, any issue fixed is covered by one or more new tests
- Verify that all tests pass using `./gradlew check`
- Sign your commits using the `--signoff` git flag
- Push your code to your fork of the repository
- Make a Pull Request

# Commit mesages

- Separate subject from body with a blank line
- Limit the subject line to 50 characters
- Capitalize the subject line
- Do not end the subject line with a period
- Use the imperative mood in the subject line
    > "Add x", "Fix y", "Support z", "Remove x"
- Wrap the body at 72 characters
- Use the body to explain what and why vs. how

For comprehensive explanation read this post by Chris Beams.